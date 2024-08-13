package com.dubbo.demo.provider;


import com.dubbo.demo.FrameworkNotifyService;
import com.dubbo.entity.RequestEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("frameworkNotifyService")
public class FrameworkNotifyServiceImpl implements FrameworkNotifyService {

    private static final Logger logger = LoggerFactory.getLogger(FrameworkNotifyServiceImpl.class);



    @Override
    public void onInvoke(RequestEntity request) {
        logger.info("pay invoke before....");
    }

    @Override
    public void onReturn(RequestEntity request) {
        logger.info("generate order...");
        logger.info("send message...");
    }

    @Override
    public void onThrow(RequestEntity request) {
        logger.info("invoke onThrow.");
    }
}
