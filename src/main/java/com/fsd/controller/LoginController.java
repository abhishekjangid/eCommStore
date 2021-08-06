package com.fsd.controller;

import com.fsd.model.SecurityContext;
import com.fsd.service.impl.LoginServiceImpl;
import com.fsd.validators.Patterns;

import java.util.Scanner;

import static com.fsd.validators.ValidationEngine.validate;

public class LoginController {
    public static boolean startLogin() {
        Scanner consoleScan = new Scanner(System.in);
        SecurityContext.clearContext();

        System.out.println("+================================+");
        System.out.println("| Welcome to Login Process!");
        System.out.println("+================================+");

        System.out.println("Enter user id and Password:");
        String userId = "";
        String password = "";
        boolean flag = true;
        boolean isValidCredentails = true;
        while(flag) {
            System.out.println("User Id : ");
            userId = consoleScan.nextLine();
            System.out.println("Password : ");
            password = consoleScan.nextLine();
            System.out.println();
            isValidCredentails = validate(Patterns.USERNAME.matcher(userId)::matches)
                                && validate(Patterns.PASSWORD.matcher(password)::matches);
            if(!isValidCredentails) {
                System.out.println("Bad Input, Do you want to re-enter credentials: Y/N");
                String retry = consoleScan.nextLine();
                if(retry != null && retry.equalsIgnoreCase("y")){
                    continue;
                }
            }
            flag = false;
        }

        SecurityContext context = null;
        if(isValidCredentails) {
            context = new LoginServiceImpl().login(userId, password);
        }
        System.out.println("+================================+");
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
