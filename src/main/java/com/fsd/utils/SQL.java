package com.fsd.utils;

public class SQL {
    public static final String LOGIN_SQL = "SELECT * FROM ECOMFSD.BUYER WHERE USERNAME = ? AND PASSWD = ?";
    public static final String REGISTER_SQL = "INSERT INTO ECOMFSD.BUYER(USERNAME, PASSWD, CONTNUM, EMAIL, ADDRESS, DOJ) " +
            "VALUES (?, ?, ?, ?, ?, SYSDATE());";
}
