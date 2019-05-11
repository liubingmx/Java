package com.bing.dp.strategy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

import java.util.HashMap;
import java.util.Set;

/**
 * @author: bing
 * @create: 2019/05/10 10:33
 */
@Component
public class HandlerProcessor implements BeanFactoryPostProcessor, ApplicationContextAware {

    private static Logger logger = LoggerFactory.getLogger(HandlerProcessor.class);

    private ApplicationContext applicationContext;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        logger.info(" start scan strategy handler ... ");
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(HandlerType.class));
        provider.addIncludeFilter(new AssignableTypeFilter(Strategy.class));
        Set<BeanDefinition> candidateComponents = provider.findCandidateComponents("com.bing.dp.strategy.handler");
        HashMap<Integer, Strategy> handlers = new HashMap<>(candidateComponents.size());
        candidateComponents.forEach(definition ->{
            String beanClassName = definition.getBeanClassName();
            if (beanClassName == null) {
                throw new IllegalStateException(String.format("Unable to obtain bean class name from bean definition %s!", definition));
            }
            try {
                Class<Strategy> handler = (Class<Strategy>) ClassUtils.forName(beanClassName, this.getClass().getClassLoader());
                HandlerType handlerType = handler.getAnnotation(HandlerType.class);
                if (handlerType != null && handlers.containsKey(handlerType.value().getType() )) {
                    throw new RuntimeException(String.format(" already exist handlerType 【%s】", handlerType.value()) );
                }
                handlers.put(handlerType.value().getType(), applicationContext.getBean(handler));
            } catch (ClassNotFoundException var12) {
                throw new IllegalStateException(var12);
            }
        });

        StrategyFactory strategyFactory = new StrategyFactory(handlers);
        configurableListableBeanFactory.registerSingleton("strategyFactory", strategyFactory);
        logger.info(" strategyFactory init end  ... ");

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
