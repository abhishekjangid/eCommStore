package com.fsd.controller;

import com.fsd.model.SecurityContext;
import com.fsd.service.impl.LoginServiceImpl;

import java.util.Scanner;

public class LoginController {
    public static boolean startLogin() {
        Scanner consoleScan = new Scanner(System.in);

        System.out.println("+================================+");
        System.out.println("| Welcome to Login Process!");
        System.out.println("+================================+");

        System.out.println("Enter user id and Password:");
        System.out.println("User Id : ");
        String userId = consoleScan.nextLine();
        System.out.println("Password : ");
        String password = consoleScan.nextLine();
        System.out.println();

        SecurityContext context = new LoginServiceImpl().login(userId, password);

        System.out.println("+================================+" + context);
        if(context != null) {
            System.out.println("| Welcome " + context.getUserName());
            if(context.getUserType().equalsIgnoreCase("B")) {
                BuyerController.start(context);
            } else {
                SellerController.start(context);
            }
        } else {
            System.out.println("+================================+");
            System.out.println("| Login failed!");
            System.out.println("+================================+");
        }

        return true;
    }
}
