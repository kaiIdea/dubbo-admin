package com.dubbo.demo.provider;

import com.dubbo.demo.LockerCasService;
import com.dubbo.demo.PayFacade;
import com.dubbo.entity.LockerCas;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.DubboService;
import org.apache.dubbo.config.annotation.Method;
import org.springframework.beans.factory.annotation.Autowired;


@DubboService
public class PayFacadeImpl implements PayFacade {


    @Autowired
    @DubboReference(
            methods = {@Method(
                    name = "getCasMessage",
                    oninvoke = "frameworkNotifyService.onInvoke",
                    onreturn = "frameworkNotifyService.onReturn",
                    onthrow = "frameworkNotifyService.onThrow"
            )}
    )
    LockerCasService lockerCasService;


    @Override
    public LockerCas recvPay(String context) {
        try {
            return lockerCasService.getCasMessage(context);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new LockerCas("invoke exception.");
    }

}
