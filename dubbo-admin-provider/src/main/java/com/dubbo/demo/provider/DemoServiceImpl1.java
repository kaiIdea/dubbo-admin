package com.dubbo.demo.provider;

import com.dubbo.demo.DemoService;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService(group = "V1.0.1")
public class DemoServiceImpl1 implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Bye "+name;
    }

    @Override
    public String getMessage() {
        return null;
    }
}
