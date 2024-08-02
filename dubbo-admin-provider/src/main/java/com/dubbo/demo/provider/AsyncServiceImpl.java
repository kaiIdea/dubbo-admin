package com.dubbo.demo.provider;

import com.dubbo.demo.AsyncService;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.AsyncContext;
import org.apache.dubbo.rpc.RpcContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;


@DubboService(timeout = 4000)
public class AsyncServiceImpl implements AsyncService {

    private static final Logger logger = LoggerFactory.getLogger(AsyncServiceImpl.class);
    @Override
    public CompletableFuture<String> tallOrder(String context) {
        return CompletableFuture.supplyAsync(new Supplier<String>() {
            @Override
            public String get() {
                logger.info("provider-side tall order run begin.");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                logger.info("provider-side tall order run end.");
                RpcContext.getServerContext().setAttachment("data","order is very good");
                return "order success.";
            }
        });
    }

    @Override
    public String tallOrder1(String context) {
        AsyncContext asyncContext = RpcContext.startAsync();
        new Thread(() -> {
            asyncContext.signalContextSwitch();
            logger.info("provider-side tall order1 run begin.");
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            asyncContext.write("order1 success.");
        }).start();
        logger.info("provider-side tall order1 run end.");
        return null;
    }
}
