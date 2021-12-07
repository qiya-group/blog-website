package org.blog.server.common;

import org.blog.server.aspect.ApiAspect;
import org.blog.server.common.annotion.ApiAuth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;
import java.util.*;

@Component
public class MappingLoader {
    private final static Logger logger = LoggerFactory.getLogger(MappingLoader.class);

    @Autowired
    WebApplicationContext applicationContext;

    public Map<String, ControllerMapping> getAllUrl() {
        // 获取springmvc处理器映射器组件对象 RequestMappingHandlerMapping无法直接注入
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        Map<String, ControllerMapping> maps = new HashMap<String, ControllerMapping>();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            // 获取url
            PatternsRequestCondition p = info.getPatternsCondition();
            for (String url : p.getPatterns()) {
                ControllerMapping controllerMapping = new ControllerMapping();
                controllerMapping.setController(method.getMethod().getDeclaringClass().getName());
                controllerMapping.setMethod(method.getMethod().getName());
                maps.put(url, controllerMapping);
            }
        }
        return maps;
    }

    public List<String> findApiAuths(ControllerMapping controller) {
        List<String> auths = new LinkedList<>();
        try {
            Class clazz = Class.forName(controller.getController());
            Method method = clazz.getMethod(controller.getMethod());
            boolean annotationPresent = method.isAnnotationPresent(ApiAuth.class);
            if (annotationPresent) {
                // 获取自定义注解对象
                ApiAuth methodAnno = method.getAnnotation(ApiAuth.class);
                // 根据对象获取注解值
                String[] roles = methodAnno.role();
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
