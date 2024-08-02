package com.dubbo.demo;

import com.dubbo.entity.LockerCas;

import java.sql.SQLException;

public interface LockerCasService {


    public LockerCas getCasMessage(int index) throws SQLException, ClassNotFoundException;
}
