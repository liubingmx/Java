package com.bing.dp.strategy;

/**
 * @author: bing
 * @create: 2019/05/10 10:22
 */
public interface Strategy{

    Result handle(Request request) throws Exception;
}
