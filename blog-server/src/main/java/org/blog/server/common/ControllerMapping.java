package org.blog.server.common;

import java.lang.reflect.Method;

public class ControllerMapping {

    private Class controller;

    private Method method;


    public Class getController() {
        return controller;
    }

    public void setController(Class controller) {
        this.controller = controller;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }
}
