package com.fsd.utils;

public class SQL {
    public static final String LOGIN_SQL = "SELECT * FROM ECOMFSD.BUYER WHERE USERNAME = ? AND PASSWD = ?";
    public static final String REGISTER_SQL = "INSERT INTO ECOMFSD.BUYER(USERNAME, PASSWD, CONTNUM, EMAIL, ADDRESS, DOJ) " +
            "VALUES (?, ?, ?, ?, ?, SYSDATE());";
    public static String INSERT_PRODUCT = "INSERT INTO ECOMFSD.PRODUCT(NAME, MAKE_YEAR, MODEL_NUMBER, PRICE, SELLER_ID, CURRENT_STOCK, SPECS, REMARKS) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
    public static String UPDATE_PRODUCT_QUANTITY = "UPDATE ECOMFSD.PRODUCT SET CURRENT_STOCK = ?  WHERE ID = ?;";
    public static String UPDATE_PRODUCT_PRICE = "UPDATE ECOMFSD.PRODUCT SET PRICE = ?  WHERE ID = ?;";
    public static String GET_PRODUCT_DETAILS = "SELECT * FROM ECOMFSD.PRODUCT WHERE ID = ?;";
}
