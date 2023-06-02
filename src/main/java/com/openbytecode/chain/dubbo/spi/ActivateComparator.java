package com.openbytecode.chain.dubbo.spi;

import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * OrderComparator
 *
 * @author lijunping
 */
public class ActivateComparator implements Comparator<Class<?>> {

    private final Map<Class<?>, ActivateInfo> activateInfoMap = new ConcurrentHashMap<>();

    @Override
    public int compare(Class o1, Class o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }
        if (o1 == null) {
            return -1;
        }
        if (o2 == null) {
            return 1;
        }
        if (o1.equals(o2)) {
            return 0;
        }

        ActivateInfo a1 = parseActivate(o1);
        ActivateInfo a2 = parseActivate(o2);


        // In order to avoid the problem of inconsistency between the loading order of two filters
        // in different loading scenarios without specifying the order attribute of the filter,
        // when the order is the same, compare its filterName
        if (a1.order > a2.order) {
            return 1;
        } else if (a1.order == a2.order) {
            return o1.getSimpleName().compareTo(o2.getSimpleName()) > 0 ? 1 : -1;
        } else {
            return -1;
        }
    }

    private ActivateInfo parseActivate(Class<?> clazz) {
        ActivateInfo info = activateInfoMap.get(clazz);
        if (info != null) {
            return info;
        }
        info = new ActivateInfo();
        if (clazz.isAnnotationPresent(Activate.class)) {
            Activate activate = clazz.getAnnotation(Activate.class);
            info.order = activate.order();
        } else {
            info.order = 0;
        }
        activateInfoMap.put(clazz, info);
        return info;
    }

    private static class ActivateInfo {
        private int order;
    }
}
