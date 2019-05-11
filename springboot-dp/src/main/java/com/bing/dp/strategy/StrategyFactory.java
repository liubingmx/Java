package com.bing.dp.strategy;

import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author: bing
 * @create: 2019/05/10 10:22
 */
@Component
public class StrategyFactory {

    private HashMap<Integer, Strategy> handlers ;

    public StrategyFactory(HashMap<Integer, Strategy> handlers) {
        this.handlers = handlers;
    }

    public Strategy getStrategy(Integer type) {
        return handlers.get(type);
    }

    public Strategy getStrategy(HandlerTypeEnum type) {
        return handlers.get(type.getType());
    }

}
