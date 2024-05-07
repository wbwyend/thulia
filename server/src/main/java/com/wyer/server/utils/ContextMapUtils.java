package com.wyer.server.utils;

/**
 * Name: ContextMap
 * Writer: wyer
 **/
public class ContextMapUtils {

    private static final ThreadLocal<Integer> map = new ThreadLocal<>();

    public static void setContextId(int id) {
        map.set(id);
    }

    public static int getContextId() {
        if (map.get() == null) return 0;
        return map.get();
    }

    public static void removeContextId() {
        map.remove();
    }
}
