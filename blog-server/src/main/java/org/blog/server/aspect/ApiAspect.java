package org.blog.server.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.blog.server.common.ControllerMapping;
import org.blog.server.common.MappingLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

@Aspect
@Component
public class ApiAspect {

    private final static Logger logger = LoggerFactory.getLogger(ApiAspect.class);

    @Autowired
    private MappingLoader mappingLoader;

    /**
     * 定义切面，拦截所有controller方法
     */
    @Pointcut("@within(org.springframework.web.bind.annotation.RestController)")
    public void controllerAuth() {
    }

    @Before("controllerAuth()")
    public void doBeforeController(JoinPoint joinPoint) throws Exception {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();
        String url = request.getRequestURI();
        String role = request.getHeader("role");
        ControllerMapping controllerMapping = this.mappingLoader.getAllUrl().get(url);
        List<String> roles = this.mappingLoader.findApiAuths(controllerMapping);
        // 遍历角色权限
        for (String s : roles) {
            if (!s.equals(role)) {
                throw new Exception("鉴权失败！");
            }
        }

        logger.info("鉴权通过！");
    }
}
