package org.blog.server.common;

import org.blog.server.common.annotion.ApiAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import java.lang.reflect.Method;
import java.util.*;

@Component
public class MappingLoader {
    private final static Logger logger = LoggerFactory.getLogger(MappingLoader.class);

    public List<String> findApiAuths(ControllerMapping controller) {
        List<String> auths = new LinkedList<>();
        try {
            Method method = controller.getMethod();
            boolean annotationPresent = method.isAnnotationPresent(ApiAuth.class);
            if (annotationPresent) {
                // 获取自定义注解对象
                ApiAuth methodAnnotation = method.getAnnotation(ApiAuth.class);
                // 根据对象获取注解值
                String[] roles = methodAnnotation.role();
                auths.addAll(Arrays.asList(roles));
                return auths;
            }
            logger.warn("当前方法未添加权限验证!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return auths;
    }
}
