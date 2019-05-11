package com.bing.dp;

import com.bing.dp.strategy.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: bing
 * @create: 2019/05/10 16:49
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class StrategyTest {

    @Autowired
    StrategyFactory strategyFactory;

    @Test
    public void testStrategy() throws Exception {
        Request request = new Request();
        request.setA(2);
        request.setB(4);
        Result handle = strategyFactory.getStrategy(HandlerTypeEnum.ADD).handle(request);
        Assert.assertEquals(6, handle.getResult());
        Result handle1 = strategyFactory.getStrategy(HandlerTypeEnum.MULTIPLY).handle(request);
        Assert.assertEquals(8, handle1.getResult());
    }


}
