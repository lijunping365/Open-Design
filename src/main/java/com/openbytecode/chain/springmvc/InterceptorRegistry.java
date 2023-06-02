package com.openbytecode.chain.springmvc;

import org.springframework.core.OrderComparator;
import org.springframework.core.Ordered;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lijunping
 */
public class InterceptorRegistry {

    private final List<InterceptorRegistration> registrations = new ArrayList<>();


    /**
     * Adds the provided {@link org.springframework.web.servlet.HandlerInterceptor}.
     * @param interceptor the interceptor to add
     * @return an {@link InterceptorRegistration} that allows you optionally configure the
     * registered interceptor further for example adding URL patterns it should apply to.
     */
    public InterceptorRegistration addInterceptor(HandlerInterceptor interceptor) {
        InterceptorRegistration registration = new InterceptorRegistration(interceptor);
        this.registrations.add(registration);
        return registration;
    }

    /**
     * Return all registered interceptors.
     */
    public List<Object> getInterceptors() {
        return this.registrations.stream()
                .sorted(INTERCEPTOR_ORDER_COMPARATOR)
                .map(InterceptorRegistration::getInterceptor)
                .collect(Collectors.toList());
    }


    private static final Comparator<Object> INTERCEPTOR_ORDER_COMPARATOR =
            OrderComparator.INSTANCE.withSourceProvider(object -> {
                if (object instanceof InterceptorRegistration) {
                    return (Ordered) ((InterceptorRegistration) object)::getOrder;
                }
                return null;
            });
}
