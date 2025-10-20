package com.example.app.proxy;

import com.example.app.aop.handler.SimpleHandler;
import com.example.app.aop.util.MethodMatcher;
import com.example.app.aop.util.NameMatchMethodMatcher;
import com.example.app.service.HelloService;
import com.example.app.service.HelloServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Proxy;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
public class ProxyTests {
    @Test
    public void testSayHello(){
        HelloService target = new HelloServiceImpl();
        MethodMatcher matcher = new NameMatchMethodMatcher("sayHello"); // PointCut
        HelloService proxy = (HelloService) Proxy.newProxyInstance(
                HelloService.class.getClassLoader(),
                new Class[] {HelloService.class},
                new SimpleHandler(target, matcher)
        );

        String result = proxy.sayHello("ted");
        assertThat("hello ted").isEqualTo(result);
    }
}















