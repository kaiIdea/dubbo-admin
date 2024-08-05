package com.dubbo.demo;

import com.dubbo.entity.LockerCas;

import java.sql.SQLException;

public interface LockerCasService {


    public LockerCas getCasMessage(Integer index) throws SQLException, ClassNotFoundException;
    public LockerCas getCasMessage(String index) throws SQLException, ClassNotFoundException;
}
