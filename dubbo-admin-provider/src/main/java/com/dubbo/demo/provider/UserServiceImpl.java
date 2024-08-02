package com.dubbo.demo.provider;

import com.dubbo.demo.UserService;
import com.dubbo.entity.LockerUser;
import org.apache.dubbo.config.annotation.DubboService;


@DubboService
public class UserServiceImpl implements UserService {



    @Override
    public LockerUser getUser(Integer id) {
        String sql = "SELECT * FROM locker_cas_1 where id = ?";
        return null;
    }
}
