package org.blog.server.common.runner;

import org.blog.server.common.ControllerMapping;
import org.blog.server.common.ControllerPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.util.Map;

@Component
@Order(1)
public class FirstLoadRunner implements CommandLineRunner {

    @Autowired
    private WebApplicationContext applicationContext;

    private final static Logger logger = LoggerFactory.getLogger(FirstLoadRunner.class);

    private final ControllerPool controllerPool = ControllerPool.getInstance();

    @Override
    public void run(String... args) {
        logger.info("Level1 Runner!");
        this.collectControllers();
    }

    public void collectControllers() {
        logger.info("start init collect controllers");
        // 获取springmvc处理器映射器组件对象 RequestMappingHandlerMapping无法直接注入
        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
        //获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
        for (Map.Entry<RequestMappingInfo, HandlerMethod> m : map.entrySet()) {
            RequestMappingInfo info = m.getKey();
            HandlerMethod method = m.getValue();
            // 获取url
            PatternsRequestCondition p = info.getPatternsCondition();
            assert p != null;
            for (String url : p.getPatterns()) {
                ControllerMapping controllerMapping = new ControllerMapping();
                controllerMapping.setController(method.getMethod().getDeclaringClass().getName());
                controllerMapping.setMethod(method.getMethod().getName());
                this.controllerPool.put(url, controllerMapping);
                logger.info(String.format("Url: %s | ControllerName: %s", url, controllerMapping.getMethod()));
            }
        }
        logger.info("collect controller finish!");
    }
}
