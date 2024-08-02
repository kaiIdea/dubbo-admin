package com.dubbo.demo.consumer.controller;

import com.dubbo.demo.AsyncService;
import com.dubbo.demo.LockerCasService;
import com.dubbo.entity.LockerCas;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @DubboReference
    LockerCasService lockerCasService;

    @DubboReference(timeout = 6000)
    AsyncService asyncService;

    @GetMapping("/getHello/{index}")
    public LockerCas getHello(@PathVariable int index){
        try {
            LockerCas casMessage = lockerCasService.getCasMessage(index);
            String data = RpcContext.getServerContext().getAttachment("data");
            logger.info("*******attachment: {}",data);
            return casMessage;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/getFuture")
    public String getFuture() throws ExecutionException, InterruptedException {
        logger.info("*****************************************************tall order run...");
        CompletableFuture<String> future = asyncService.tallOrder("tall order.");
        logger.info("【【【【【【【【【【【【【【【【【【【【【【【【【【【【tall order rpc invoke success.");
        String result = future.get();
        logger.info("tall order future get success.");
        return result;
    }

    @GetMapping("/getFuture1")
    public String getFuture1() throws ExecutionException, InterruptedException {
        logger.info("*****************************************************tall order1 run...");
        String result = asyncService.tallOrder1("tall order1.");
        logger.info("【【【【【【【【【【【【【【【【【【【【【【【【【【【【tall order1 rpc invoke success.");
        logger.info("tall order1 future get success.");
        return result;
    }
}
