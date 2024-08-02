package com.dubbo.demo.jdbc;

import com.dubbo.entity.LockerCas;

import java.sql.*;

public class JDBCProcess {


    public static Object getResult(String sql) throws SQLException, ClassNotFoundException {
        //准备信息
        Connection conn = getConnection();
        PreparedStatement prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setInt(1,3);
        ResultSet resultSet = prepareStatement.executeQuery();
        LockerCas cas = new LockerCas();

        if (resultSet.next()) {
            cas.setId(resultSet.getInt("id"));
            cas.setCasNo(resultSet.getString("cas_no"));
            cas.setSmiles(resultSet.getString("smiles"));
            cas.setCid(resultSet.getString("cid"));
            cas.setCnName(resultSet.getString("cn_name"));
            cas.setProperties(resultSet.getString("properties"));
        }
        resultSet.close();
        conn.close();
        return cas;
    }

    public static LockerCas getLockerCas(String sql,int index) throws SQLException, ClassNotFoundException {
        //准备信息
        Connection conn = getConnection();
        PreparedStatement prepareStatement = conn.prepareStatement(sql);
        prepareStatement.setInt(1,index);
        ResultSet resultSet = prepareStatement.executeQuery();
        LockerCas cas = new LockerCas();
        if (resultSet.next()) {
            cas.setId(resultSet.getInt("id"));
            cas.setCasNo(resultSet.getString("cas_no"));
            cas.setSmiles(resultSet.getString("smiles"));
            cas.setCid(resultSet.getString("cid"));
            cas.setCnName(resultSet.getString("cn_name"));
            cas.setProperties(resultSet.getString("properties"));
        }
        resultSet.close();
        conn.close();
        return cas;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM locker_cas_1 where id = ?";
        getResult(sql);
        System.out.println();
    }

    private static Connection getConnection() throws ClassNotFoundException, SQLException {
        String className = "com.mysql.cj.jdbc.Driver";
        String url = "jdbc:mysql://localhost:3306/es";
        String username = "root";
        String password = "root";
        Class.forName(className);
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }


}
