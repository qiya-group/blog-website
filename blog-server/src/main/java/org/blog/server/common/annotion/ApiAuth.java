package org.blog.server.common.annotion;

import java.lang.annotation.*;

/**
 * 权限定义注解
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ApiAuth {
    /**
     * 当前角色
     */
    String[] role();
}
