package com.bing.security.server;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServerApplicationTests {

    @Test
    public void contextLoads() {
        System.out.println(UUID.randomUUID().toString());
    }

}
