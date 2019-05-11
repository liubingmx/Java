package com.bing.dp.strategy.handler;

import com.bing.dp.strategy.*;
import org.springframework.stereotype.Component;

/**
 * @author: bing
 * @create: 2019/05/11 10:25
 */
@Component
@HandlerType(HandlerTypeEnum.SUB)
public class StrategySub implements Strategy {

    @Override
    public Result handle(Request request) throws Exception {
        Result result = new Result();
        result.setResult(request.getA() + request.getB());
        return result;
    }
}
