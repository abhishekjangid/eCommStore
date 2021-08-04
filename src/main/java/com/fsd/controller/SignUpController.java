package com.fsd.controller;

import com.fsd.model.User;
import com.fsd.service.impl.LoginServiceImpl;

import java.util.Scanner;

public class SignUpController {
    public static boolean startRegistration() {
        Scanner consoleScan = new Scanner(System.in);

        System.out.println("+================================+");
        System.out.println("| Welcome to Sign Up Process!");
        System.out.println("+================================+");

        System.out.println("Enter user id and Password:");
        User user = new User();
        System.out.println("User Id : ");
        user.setUserName(consoleScan.nextLine());
        System.out.println("Password : ");
        user.setPassword(consoleScan.nextLine());
        System.out.println("Contact no : ");
        user.setContactNumber(consoleScan.nextLine());
        System.out.println("Email : ");
        user.setEmail(consoleScan.nextLine());
        System.out.println("Address : ");
        user.setAddress(consoleScan.nextLine());
        System.out.println();

        boolean isSignUpSuccess = new LoginServiceImpl().signUp(user);

        if(isSignUpSuccess) {
            System.out.println("+================================+");
            System.out.println("| Sign up Successful!");
            System.out.println("+================================+");
        } else {
            System.out.println("+================================+");
            System.out.println("| Sign up failed!");
            System.out.println("+================================+");
        }

        return true;
    }
}
