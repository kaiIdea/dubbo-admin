package com.dubbo.demo;

import com.dubbo.entity.RequestEntity;

public interface FrameworkNotifyService {

    void onInvoke(RequestEntity request);

    void onReturn(RequestEntity request);

    void onThrow(RequestEntity request);
}
