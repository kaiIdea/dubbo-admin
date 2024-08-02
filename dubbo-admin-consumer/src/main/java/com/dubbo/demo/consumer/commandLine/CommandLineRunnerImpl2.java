//package com.dubbo.demo.consumer.commandLine;
//
//import com.dubbo.demo.AsyncService;
//import org.apache.dubbo.config.annotation.DubboReference;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.concurrent.CompletableFuture;
//
//
//@Component
//public class CommandLineRunnerImpl2 implements CommandLineRunner {
//
//
//    private static final Logger logger = LoggerFactory.getLogger(CommandLineRunnerImpl2.class);
//    @DubboReference(timeout = 6000)
//    AsyncService asyncService;
//
//
//    @Override
//    public void run(String... args) throws Exception {
//        logger.info("*****************************************************tall order run...");
//        CompletableFuture<String> future = asyncService.tallOrder("tall order.");
//        logger.info("【【【【【【【【【【【【【【【【【【【【【【【【【【【【tall order rpc invoke success.");
//        future.get();
//        logger.info("tall order future get success.");
//    }
//}
