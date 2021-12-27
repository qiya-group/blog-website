package org.blog.server.common.error;

/**
 * SQL注入安全异常
 */
public class SQLInjectException extends Exception{
    public SQLInjectException() {
        super();
    }

    public SQLInjectException(String message) {
        super(message);
    }
}
