package com.dubbo.demo.consumer.generic;

import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenericDubboInvoke {

    private static final Logger logger = LoggerFactory.getLogger(GenericDubboInvoke.class);

    private static GenericService genericService;

    public static void main(String[] args) {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("generic-admin-consumer");

        // 连接注册中心配置
        RegistryConfig registryConfig = new RegistryConfig("nacos://127.0.0.1:8848");
        registryConfig.setTimeout(60000);

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");


        ReferenceConfig<GenericService> referenceConfig = new ReferenceConfig<>();
        referenceConfig.setInterface("com.dubbo.demo.OrderService");

        referenceConfig.setApplication(application);
        referenceConfig.setRegistry(registryConfig);
        referenceConfig.setVersion("1.0.0");

        referenceConfig.setGeneric(true);

        referenceConfig.setTimeout(7000);

        genericService = referenceConfig.get();

        Object result = genericService.$invoke("getOrderMessage", new String[]{"java.lang.String"}, new Object[]{"quickly money"});

        logger.info("******************************************return result: {}",result);

    }
}
