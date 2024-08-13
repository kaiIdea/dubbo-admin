package com.dubbo.demo.consumer.controller;

import com.dubbo.demo.AsyncService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/async")
public class AsyncController {

    private static final Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @DubboReference(timeout = 6000,check = false)
    AsyncService asyncService;


    @GetMapping("/getFuture")
    public String getFuture() throws ExecutionException, InterruptedException {
        logger.info("***tall order run...");
        CompletableFuture<String> future = asyncService.tallOrder("tall order.");
        AtomicReference<String> result = null;
        logger.info("tall order rpc invoke success.");
        String data = RpcContext.getServerContext().getAttachment("data");
        future.whenComplete((s, throwable) -> {
            if(null != throwable){
                throwable.printStackTrace();
            }else {
                String data1 = RpcContext.getServerContext().getAttachment("data");
                logger.info("tall order future return: {}，{}",s,data1);
            }
        });
        String data2 = RpcContext.getServerContext().getAttachment("data");
        logger.info("tall order future end.");
        return "tall order future get success.";
    }

    @GetMapping("/getFuture1")
    public String getFuture1() throws ExecutionException, InterruptedException {
        logger.info("***tall order1 run...");
        String result = asyncService.tallOrder1("tall order1.");
        logger.info("tall order1 rpc invoke success.");
        logger.info("tall order1 future get success.");
        return result;
    }
}

