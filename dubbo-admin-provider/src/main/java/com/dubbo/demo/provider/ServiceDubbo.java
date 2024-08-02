package com.dubbo.demo.provider;

import com.dubbo.demo.DemoService;
import com.dubbo.demo.LockerCasService;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.io.IOException;

public class ServiceDubbo {

//    public static void main(String[] args) {
//
//
//        ServiceConfig<DemoService> demoServiceConfig = new ServiceConfig<>();
//        demoServiceConfig.setInterface(DemoService.class);
//        demoServiceConfig.setRef(new DemoServiceImpl());
//        demoServiceConfig.setVersion("1.0.0");
//
//        ServiceConfig<LockerCasService> casServiceConfig = new ServiceConfig<>();
//        casServiceConfig.setInterface(LockerCasService.class);
//        casServiceConfig.setRef(new LockerCasServiceImpl());
//        casServiceConfig.setVersion("1.0.0");
//
//        ProtocolConfig protocol = new ProtocolConfig();
//        protocol.setName("dubbo");
//
//        //RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.109.128:2181");
//        RegistryConfig registryConfig = new RegistryConfig("nacos://127.0.0.1:8848");
//        registryConfig.setTimeout(60000);
//        DubboBootstrap.getInstance()
//                .application("dubbo-admin-provider")
//                .registry(registryConfig)
//                .protocol(protocol)
//                .service(casServiceConfig)
//                .service(demoServiceConfig)
//                //服务启动
//                .start()
//                //进程挂起，防止程序退出
//                .await();
//
//
//    }

//    public static void main(String[] args) {
//        ConfigCenterConfig configCenter = new ConfigCenterConfig();
//        configCenter.setAddress("nacos://127.0.0.1:8848");
//
//        // 服务提供者协议配置
//        ProtocolConfig protocol = new ProtocolConfig();
//        protocol.setName("dubbo");
//
//        ServiceConfig<DemoService> demoServiceConfig = new ServiceConfig<>();
//        demoServiceConfig.setInterface(DemoService.class);
//        demoServiceConfig.setRef(new DemoServiceImpl());
//        demoServiceConfig.setVersion("1.0.0");
//
//        ServiceConfig<LockerCasService> casServiceConfig = new ServiceConfig<>();
//        casServiceConfig.setInterface(LockerCasService.class);
//        casServiceConfig.setRef(new LockerCasServiceImpl());
//        casServiceConfig.setVersion("1.0.0");
//
//        RegistryConfig registryConfig = new RegistryConfig("nacos://127.0.0.1:8848");
//        registryConfig.setTimeout(60000);
//        DubboBootstrap.getInstance()
//                .application("dubbo-admin-provider1")
//                .registry(registryConfig)
//                .protocol(protocol)
//                .service(casServiceConfig)
//                .service(demoServiceConfig)
//                //服务启动
//                .start()
//                //进程挂起，防止程序退出
//                .await();
//    }

    public static void main(String[] args) throws IOException {
        ApplicationConfig application = new ApplicationConfig();
        application.setName("dubbo-admin-provider");

        // 连接注册中心配置
        RegistryConfig registryConfig = new RegistryConfig("nacos://127.0.0.1:8848");
        registryConfig.setTimeout(60000);
        registryConfig.setGroup("me dubbo");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");




        ServiceConfig<DemoService> demoServiceConfig = new ServiceConfig<>();
        demoServiceConfig.setInterface(DemoService.class);
        demoServiceConfig.setRef(new DemoServiceImpl());
        demoServiceConfig.setVersion("1.0.0");
        demoServiceConfig.setRegistry(registryConfig);
        demoServiceConfig.setProtocol(protocol);
        demoServiceConfig.setApplication(application);
        demoServiceConfig.export();



        ServiceConfig<LockerCasService> casServiceConfig = new ServiceConfig<>();
        casServiceConfig.setInterface(LockerCasService.class);
        casServiceConfig.setRef(new LockerCasServiceImpl());
        casServiceConfig.setVersion("1.0.0");
        casServiceConfig.setRegistry(registryConfig);
        casServiceConfig.setProtocol(protocol);
        casServiceConfig.setApplication(application);
        casServiceConfig.export();


        System.in.read();
    }
}
