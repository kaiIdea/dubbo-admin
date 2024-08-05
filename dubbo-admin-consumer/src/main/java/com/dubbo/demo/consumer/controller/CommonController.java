package com.dubbo.demo.consumer.controller;


import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/common")
public class CommonController {

    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

    @GetMapping("/gateway/{className}/{methodName}/{parameterTypeName}/{reqBody}/request")
    public String commonRequest(
            @PathVariable String className,
            @PathVariable String methodName,
            @PathVariable String parameterTypeName,
            @PathVariable Object reqBody ){

        ReferenceConfig<GenericService> referenceConfig = createReferenceConfig(className);
        GenericService genericService = referenceConfig.get();
        Object result = genericService.$invoke(methodName, new String[]{parameterTypeName}, new Object[]{reqBody});
        logger.info("******* result: {}",result);
        return result.toString();
    }

    private ReferenceConfig<GenericService> createReferenceConfig(String className) {
        DubboBootstrap instance = DubboBootstrap.getInstance();
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(instance.getApplication().getName());

        // 连接注册中心配置
        RegistryConfig registryConfig = new RegistryConfig("nacos://127.0.0.1:8848");
        registryConfig.setTimeout(60000);


        ReferenceConfig<GenericService> config = new ReferenceConfig<>();
        config.setApplication(applicationConfig);
        config.setRegistry(registryConfig);

        config.setInterface(className);
        config.setGeneric("true");
        config.setTimeout(6000);

        return config;
    }
}
