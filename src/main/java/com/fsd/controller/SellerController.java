package com.fsd.controller;

import com.fsd.model.Product;
import com.fsd.service.impl.SellerServiceImpl;

public class SellerController {
    public static void start() {
        System.out.println("+================================+");
        System.out.println("| What you want to sell today?");
        System.out.println("+================================+");

        Product product =  new Product();
        product.setProductName("Computer");
        product.setMakeYear(2015);
        product.setModelNumber("ABCD1214XYZ");
        product.setCategory("Electronics");
        product.setPrice(25000.00F);
        product.setSellerId(1000000002);
        product.setCurrentNumbersStock(25);
        product.setSpecifications("Computer");
        product.setRemarks("Nothing to say");

        new SellerServiceImpl().addProduct(product);
    }
}
