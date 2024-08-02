package com.dubbo.demo.provider;

import org.apache.dubbo.rpc.service.GenericException;
import org.apache.dubbo.rpc.service.GenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CompletableFuture;


public class GenericImplOfOrderService implements GenericService {

    private static final Logger logger = LoggerFactory.getLogger(GenericImplOfOrderService.class);

    @Override
    public Object $invoke(String method, String[] parameterTypes, Object[] args) throws GenericException {
        if(method.equals("getOrderMessage")){
            logger.info("executing getOrderMessage.");
            throw new RuntimeException("order pay exception");
        } else if (method.equals("payOrderAsync")) {
            logger.info("executing payOrderAsync.");
            return CompletableFuture.completedFuture("payOrderAsync run success. params："+parameterTypes[0]);
        }else {
            try {
                return defaultOperation(method, parameterTypes, args);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Object defaultOperation(String method, String[] parameterTypes, Object[] args) throws Exception {
        throw new UnsupportedOperationException("method does not exist.");
    }

}
