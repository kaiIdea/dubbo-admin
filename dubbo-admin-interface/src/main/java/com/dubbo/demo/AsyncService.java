package com.dubbo.demo;

import java.util.concurrent.CompletableFuture;

public interface AsyncService {

    CompletableFuture<String> tallOrder(String context);


    String tallOrder1(String context);
}
