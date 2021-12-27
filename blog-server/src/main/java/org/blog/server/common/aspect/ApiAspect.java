package org.blog.server.common.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.blog.server.common.ControllerMapping;
import org.blog.server.common.ControllerPool;
import org.blog.server.common.MappingLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Aspect
@Component
public class ApiAspect {

    private final static Logger logger = LoggerFactory.getLogger(ApiAspect.class);
    private final ControllerPool controllerPool = ControllerPool.getInstance();

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
        String url = request.getRequestURI();
        String role = request.getHeader("role");
        ControllerMapping controllerMapping = this.controllerPool.get(url);
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
