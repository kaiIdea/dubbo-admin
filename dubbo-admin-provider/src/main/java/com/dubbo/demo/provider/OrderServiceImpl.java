package com.dubbo.demo.provider;

import com.dubbo.demo.DemoService;
import com.dubbo.demo.OrderService;
import com.dubbo.entity.LockerCas;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;


@DubboService(delay = 5000)
public class OrderServiceImpl implements OrderService {

    private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);


    @Override
    public String getOrderMessage(String context) {
        return "orderMessage: "+context;
    }

    @Override
    public CompletableFuture<String> payOrderAsync(String context) {
        CompletableFuture<String> future = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                future.complete("payOrderAsync: "+context);
            }
        }).start();
        return future;
    }

    @Override
    public CompletableFuture<LockerCas> payOrderAsyncComplex(String context) {
        LockerCas lockerCas = new LockerCas();
        lockerCas.setCnName(context);
        lockerCas.setId(888);
        CompletableFuture<LockerCas> future = new CompletableFuture<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                future.complete(lockerCas);
            }
        }).start();
        return future;
    }

    public static void main(String[] args) throws IOException {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("generic-admin-provider");

        // 连接注册中心配置
        RegistryConfig registryConfig = new RegistryConfig("nacos://127.0.0.1:8848");
        registryConfig.setTimeout(60000);

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");


        GenericService genericOrderService = new GenericImplOfOrderService();



        ServiceConfig<GenericService> orderServiceConfig = new ServiceConfig<>();
        orderServiceConfig.setInterface("com.dubbo.demo.OrderService");
        orderServiceConfig.setRef(genericOrderService);
        orderServiceConfig.setVersion("1.0.0");
        orderServiceConfig.setRegistry(registryConfig);
        orderServiceConfig.setProtocol(protocol);
        orderServiceConfig.setApplication(application);
        orderServiceConfig.setGeneric("true");
        orderServiceConfig.export();
        logger.info("genericOrderService provider start.");
        System.in.read();
    }
}
