package com.openbytecode.chain.dubbo;

import com.openbytecode.chain.dubbo.spi.ExtensionLoader;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author lijunping
 */
public class DefaultFilterChainBuilder implements FilterChainBuilder{

    /**
     * build consumer/provider filter chain
     */
    @Override
    public <T> Invoker<T> buildInvokerChain(final Invoker<T> originalInvoker, String key, String group) {
        Invoker<T> last = originalInvoker;
        List<Filter> filters = ExtensionLoader.getExtensionLoader(Filter.class).getActivateExtension(key, group);
        if (!CollectionUtils.isEmpty(filters)) {
            for (int i = filters.size() - 1; i >= 0; i--) {
                final Filter filter = filters.get(i);
                final Invoker<T> next = last;
                last = new FilterChainNode<>(originalInvoker, next, filter);
            }
            return new CallbackRegistrationInvoker<>(last, filters);
        }

        return last;
    }
}
