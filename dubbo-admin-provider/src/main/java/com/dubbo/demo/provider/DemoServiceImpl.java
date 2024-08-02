package com.dubbo.demo.provider;

import com.dubbo.demo.DemoService;
import com.dubbo.demo.LockerCasService;
import org.apache.dubbo.config.*;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;

import java.io.IOException;


@DubboService(group = "V1.0.0")
public class DemoServiceImpl implements DemoService {

    public static RegistryConfig registryConfig = null;




    @Override
        public String sayHello(String name) {
        return "Hello "+name;
    }

    @Override
    public String getMessage() {
        return null;
    }


    //以JavaAPi的方式，注册暴露服务出去
//    public static void main(String[] args) throws IOException {
//        DemoService demoService = new DemoServiceImpl();
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName("dubbo-admin-provider");
//
//
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://192.168.109.128:2181");
//        registryConfig.setTimeout(60000);
//
//        ProtocolConfig protocolConfig = new ProtocolConfig("dubbo");
//
//        ServiceConfig<DemoService> serviceConfig = new ServiceConfig<>();
//        serviceConfig.setApplication(applicationConfig);
//        serviceConfig.setRegistry(registryConfig);
//        serviceConfig.setProtocol(protocolConfig);
//
//        serviceConfig.setInterface(DemoService.class);
//        serviceConfig.setRef(demoService);
//        serviceConfig.setVersion("1.0.0");
//
//        serviceConfig.export();
//
//
//        ServiceConfig<LockerCasService> serviceConfig1 = new ServiceConfig<>();
//        serviceConfig1.setApplication(applicationConfig);
//        serviceConfig1.setRegistry(registryConfig);
//        serviceConfig1.setProtocol(protocolConfig);
//        serviceConfig1.setInterface(LockerCasService.class);
//        serviceConfig1.setRef(new LockerCasServiceImpl());
//        serviceConfig1.setVersion("1.0.0");
//        serviceConfig1.export();
//
//        //进程挂起等待
//        System.in.read();
//    }

    public static void main(String[] args) {


        ServiceConfig<DemoService> demoServiceConfig = new ServiceConfig<>();
        demoServiceConfig.setInterface(DemoService.class);
        demoServiceConfig.setRef(new DemoServiceImpl());
        demoServiceConfig.setVersion("1.0.0");

        ServiceConfig<LockerCasService> casServiceConfig = new ServiceConfig<>();
        casServiceConfig.setInterface(LockerCasService.class);
        casServiceConfig.setRef(new LockerCasServiceImpl());
        casServiceConfig.setVersion("1.0.0");

        ProtocolConfig protocol = new ProtocolConfig();
        protocol.setName("dubbo");

        //RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.109.128:2181");
        registryConfig = new RegistryConfig("nacos://127.0.0.1:8848");
        registryConfig.setTimeout(60000);
        DubboBootstrap.getInstance()
                .application("dubbo-admin-provider")
                .registry(registryConfig)
                .protocol(protocol)
                .service(demoServiceConfig)
                .service(casServiceConfig)
                //服务启动
                .start()
                //进程挂起，防止程序退出
                .await();


    }
}
