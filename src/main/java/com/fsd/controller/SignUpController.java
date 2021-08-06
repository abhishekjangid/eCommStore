package com.fsd.controller;

import com.fsd.model.User;
import com.fsd.service.impl.LoginServiceImpl;
import com.fsd.validators.Patterns;

import java.util.Scanner;

import static com.fsd.validators.ValidationEngine.validate;

public class SignUpController {
    public static boolean startRegistration() {
        Scanner consoleScan = new Scanner(System.in);

        System.out.println("+================================+");
        System.out.println("| Welcome to Sign Up Process!");
        System.out.println("+================================+");

        User user = new User();
        boolean continueSignup = false;
        do {
            System.out.println("Are you buyer (Yes/No):");
            switch(consoleScan.nextLine().toUpperCase()) {
                case "YES":
                case "Y":
                    user.setUserType('B');
                    break;
                case "No":
                case "N":
                    user.setUserType('S');
                    break;
                default:
                    continueSignup = true;
                    System.out.println("Enter valid entry.");
            }
        } while(continueSignup);
        boolean flag = true;
        boolean isValidCredentails = true;
        while(flag) {
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

            isValidCredentails = validate(Patterns.USERNAME.matcher(user.getUserName())::matches)
                    && validate(Patterns.PASSWORD.matcher(user.getPassword())::matches)
                    && validate(Patterns.EMAIL.matcher(user.getEmail())::matches)
                    && validate(Patterns.MOBILE.matcher(user.getContactNumber())::matches)
                    && validate(Patterns.ADDRESS.matcher(user.getAddress())::matches);
            if(!isValidCredentails) {
                System.out.println("Bad Input, Do you want to re-enter details: Y/N");
                String retry = consoleScan.nextLine();
                if(retry != null && retry.equalsIgnoreCase("y")){
                    continue;
                }
            }
            flag = false;
        }

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
