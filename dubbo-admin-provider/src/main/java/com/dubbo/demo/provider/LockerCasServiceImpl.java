package com.dubbo.demo.provider;

import com.dubbo.demo.LockerCasService;
import com.dubbo.demo.jdbc.JDBCProcess;
import com.dubbo.entity.LockerCas;
import org.apache.dubbo.config.ServiceConfig;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.rpc.RpcContext;

import java.sql.SQLException;


@DubboService
public class LockerCasServiceImpl implements LockerCasService {
    @Override
    public LockerCas getCasMessage(Integer index) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM locker_cas_1 where id = ?";
        LockerCas lockerCas = JDBCProcess.getLockerCas(sql, index);
        RpcContext.getServerContext().setAttachment("data","very good");
        return lockerCas;
    }

    @Override
    public LockerCas getCasMessage(String index) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM locker_cas_1 where id = ?";
        LockerCas lockerCas = JDBCProcess.getLockerCas(sql, Integer.parseInt(index));
        RpcContext.getServerContext().setAttachment("data","very good");
        return lockerCas;
    }

    public static void main(String[] args) {
        ServiceConfig<LockerCasService> casServiceConfig = new ServiceConfig<>();
        casServiceConfig.setInterface(LockerCasService.class);
        casServiceConfig.setRef(new LockerCasServiceImpl());
        casServiceConfig.setVersion("1.0.0");

//        DubboBootstrap instance = DubboBootstrap.getInstance();
//        instance
//                .application("dubbo-admin-provider")
//                .registry(DemoServiceImpl.registryConfig)
//                .protocol(new ProtocolConfig("dubbo"))
//                .service(casServiceConfig)
//                //服务启动
//                .start()
//                //进程挂起，防止程序退出
//                .await();

    }
}
