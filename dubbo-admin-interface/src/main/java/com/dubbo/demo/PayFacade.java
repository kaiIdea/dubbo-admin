package com.dubbo.demo;

import com.dubbo.entity.LockerCas;

public interface PayFacade {

    public LockerCas recvPay(String context);
}
