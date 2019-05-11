package com.bing.dp.strategy.handler;

import com.bing.dp.strategy.*;
import org.springframework.stereotype.Component;

/**
 * @author: bing
 * @create: 2019/05/10 10:25
 */
@Component
@HandlerType(HandlerTypeEnum.ADD)
public class StrategyAdd implements Strategy {

    @Override
    public Result handle(Request request) throws Exception {
        Result result = new Result();
        result.setResult(request.getA() + request.getB());
        return result;
    }
}
