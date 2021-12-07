package org.blog.server.error;

/**
 * 文章异常
 */
public class ArticleException extends Exception{
    public ArticleException() {
        super();
    }

    public ArticleException(String message) {
        super(message);
    }
}
