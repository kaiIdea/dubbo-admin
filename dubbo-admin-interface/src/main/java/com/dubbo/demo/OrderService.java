package com.dubbo.demo;

import com.dubbo.entity.LockerCas;

import java.util.concurrent.CompletableFuture;


public interface OrderService {

    String getOrderMessage(String context);

    CompletableFuture<String> payOrderAsync(String context);

    CompletableFuture<LockerCas> payOrderAsyncComplex(String context);

//    CompletableFuture<GenericType<LockerCas>> payOrderAsyncGenericComplex(String context);
}
