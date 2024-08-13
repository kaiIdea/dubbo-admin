package com.dubbo.demo.consumer.controller;

import com.dubbo.demo.PayFacade;
import com.dubbo.entity.LockerCas;
import org.apache.dubbo.config.annotation.DubboReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/pay")
public class PayController {

    private static final Logger logger = LoggerFactory.getLogger(PayController.class);

    @DubboReference(check = false)
    PayFacade payFacade;


    @GetMapping("/getHello/{index}")
    public LockerCas getHello(@PathVariable String index){
        try {
            LockerCas casMessage = payFacade.recvPay(index);
            return casMessage;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
