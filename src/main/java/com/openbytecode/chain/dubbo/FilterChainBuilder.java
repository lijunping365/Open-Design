package com.openbytecode.chain.dubbo;

import com.openbytecode.chain.dubbo.response.Result;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijunping
 */
public interface FilterChainBuilder {
    /**
     * build consumer/provider filter chain
     */
    <T> Invoker<T> buildInvokerChain(final Invoker<T> invoker, String key, String group);

    class FilterChainNode<T, TYPE extends Invoker<T>, FILTER extends Filter> implements Invoker<T> {
        TYPE originalInvoker;
        Invoker<T> nextNode;
        FILTER filter;

        public FilterChainNode(TYPE originalInvoker, Invoker<T> nextNode, FILTER filter) {
            this.originalInvoker = originalInvoker;
            this.nextNode = nextNode;
            this.filter = filter;
        }

        public TYPE getOriginalInvoker() {
            return originalInvoker;
        }

        @Override
        public Class<T> getInterface() {
            return originalInvoker.getInterface();
        }

        @Override
        public boolean isAvailable() {
            return originalInvoker.isAvailable();
        }

        @Override
        public Result invoke(Invocation invocation) throws Exception {
            Result asyncResult;
            try {
                asyncResult = filter.invoke(nextNode, invocation);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                throw e;
            }
            return asyncResult;
        }

        @Override
        public void destroy() {
            originalInvoker.destroy();
        }

        @Override
        public String toString() {
            return originalInvoker.toString();
        }
    }

    class CallbackRegistrationInvoker<T, FILTER extends Filter> implements Invoker<T> {
        final Invoker<T> filterInvoker;
        final List<FILTER> filters;

        public CallbackRegistrationInvoker(Invoker<T> filterInvoker, List<FILTER> filters) {
            this.filterInvoker = filterInvoker;
            this.filters = filters;
        }

        @Override
        public Result invoke(Invocation invocation) throws Exception {
            Result asyncResult = filterInvoker.invoke(invocation);
            asyncResult.whenCompleteWithContext((r, t) -> {
                RuntimeException filterRuntimeException = null;
                for (int i = filters.size() - 1; i >= 0; i--) {
                    FILTER filter = filters.get(i);
                    try {
                        if (filter instanceof FILTER.Listener) {
                            FILTER.Listener listener = (FILTER.Listener) filter;
                            if (t == null) {
                                listener.onResponse(r, filterInvoker, invocation);
                            } else {
                                listener.onError(t, filterInvoker, invocation);
                            }
                        }
                    } catch (RuntimeException runtimeException) {
                        System.out.printf("Exception occurred while executing the %s filter named %s.%n", i, filter.getClass().getSimpleName());
                        System.out.println((String.format("Whole filter list is: %s", filters.stream().map(tmpFilter -> tmpFilter.getClass().getSimpleName()).collect(Collectors.toList()))));
                        filterRuntimeException = runtimeException;
                        t = runtimeException;
                    }
                }
                if (filterRuntimeException != null) {
                    throw filterRuntimeException;
                }
            });

            return asyncResult;
        }

        @Override
        public Class<T> getInterface() {
            return filterInvoker.getInterface();
        }

        @Override
        public boolean isAvailable() {
            return filterInvoker.isAvailable();
        }

        @Override
        public void destroy() {
            filterInvoker.destroy();
        }
    }
}
