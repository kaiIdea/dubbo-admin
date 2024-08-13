package com.dubbo.demo.consumer.commandLine;

import com.dubbo.demo.DemoService;
import com.dubbo.demo.LockerCasService;
import com.dubbo.entity.LockerCas;
import org.apache.dubbo.config.ApplicationConfig;
import org.apache.dubbo.config.ProtocolConfig;
import org.apache.dubbo.config.ReferenceConfig;
import org.apache.dubbo.config.RegistryConfig;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.bootstrap.DubboBootstrap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


@Component
public class CommandLineRunnerImpl1 implements CommandLineRunner {

    public final static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @DubboReference(check = false,group = "V1.0.1",cluster = "failsafe")
    DemoService demoService;

    @DubboReference(check = false,group = "V1.0.0",cluster = "failsafe")
    DemoService demoService1;

    @Override
    public void run(String... args) throws Exception {
        if(null == demoService){
            return;
        }
        if(null == demoService1){
            return;
        }
//        String result = demoService.sayHello("world");
//        System.out.println("Receive result ======> " + result);
//        String result1 = demoService1.sayHello("world");
//        System.out.println("Receive result ======> " + result1);
        boolean flag = false;
        new Thread(()-> {
            while (flag) {
                try {
                    Thread.sleep(1000);
                    System.out.println(LocalDateTime.now().format(formatter) + " Receive result ======> " + demoService.sayHello("world"));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        ReferenceConfig<LockerCasService> referenceConfig = new ReferenceConfig<>();
//
//        ApplicationConfig applicationConfig = new ApplicationConfig();
//        applicationConfig.setName("dubbo-admin-consumer");
//
//        RegistryConfig registryConfig = new RegistryConfig();
//        registryConfig.setAddress("zookeeper://192.168.109.128:2181?timeout=60000");
//
//
//        referenceConfig.setApplication(applicationConfig);
//        referenceConfig.setRegistry(registryConfig);
//
//        referenceConfig.setInterface(LockerCasService.class);
//        referenceConfig.setVersion("1.0.0");
//
//        LockerCasService lockerCasService = referenceConfig.get();
//
//        Scanner scanner = new Scanner(System.in);
//        while (true){
//            System.out.print("请输入查询参数:");
//            String next = scanner.next();
//            if("quit".equals(next)){
//                System.out.println("程序退出!");
//                break;
//            }
//            Integer id = Integer.parseInt(next);
//            LockerCas casMessage = lockerCasService.getCasMessage(id);
//            System.out.println(casMessage);
//        }
//
//    }

    public static void main(String[] args) {
        ReferenceConfig<DemoService> referenceConfig = new ReferenceConfig<>();

        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("dubbo-admin-consumer");

        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("zookeeper://192.168.109.128:2181?timeout=60000");


        referenceConfig.setApplication(applicationConfig);
        referenceConfig.setRegistry(registryConfig);

        referenceConfig.setInterface(DemoService.class);
        referenceConfig.setVersion("1.0.0");

        DemoService demoService = referenceConfig.get();

        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("请输入查询参数:");
            String next = scanner.next();
            if("quit".equals(next)){
                System.out.println("程序退出!");
                break;
            }
            System.out.println(demoService.sayHello(next));;
        }

    }
//public static void main(String[] args) {
//    ReferenceConfig<DemoService> demoReferenceConfig = new ReferenceConfig<>();
//    demoReferenceConfig.setInterface(DemoService.class);
//    demoReferenceConfig.setVersion("1.0.0");
//
//
//    ReferenceConfig<LockerCasService> casReferenceConfig = new ReferenceConfig<>();
//    casReferenceConfig.setInterface(LockerCasService.class);
//    casReferenceConfig.setVersion("1.0.0");
//
//    RegistryConfig registryConfig = new RegistryConfig("zookeeper://192.168.109.128:2181");
//    registryConfig.setTimeout(60000);
//    DubboBootstrap instance = DubboBootstrap.getInstance();
//    instance
//            .application("dubbo-admin-consumer")
//            .registry(registryConfig)
//            .reference(demoReferenceConfig)
//            .reference(casReferenceConfig)
//            .start();
//
//
//
//    LockerCasService lockerCasService = DubboBootstrap.getInstance().getCache().get(casReferenceConfig);
//
//    DemoService demoService = DubboBootstrap.getInstance().getCache().get(demoReferenceConfig);
//    System.out.println();
//}
}
