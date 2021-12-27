package org.blog.server.common;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 接口池
 */
public final class ControllerPool {

    private static final ControllerPool instance = new ControllerPool();
    private Map<String, ControllerMapping> controllerMaps = new LinkedHashMap<>();

    private ControllerPool() {
    }

    public static ControllerPool getInstance() {
        return instance;
    }

    public void put(String key, ControllerMapping controllerMapping) {
        this.controllerMaps.put(key, controllerMapping);
    }

    public ControllerMapping get(String key) {
        return this.controllerMaps.get(key);
    }
}
