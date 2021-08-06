package com.fsd.controller;

import com.fsd.model.SecurityContext;

import java.util.Scanner;

public class MainController {
    Scanner consoleScan = new Scanner(System.in);

    public void start() {
        boolean continueApp = true;
        SecurityContext.clearContext();
        while(continueApp){
            switch (displayMainOptions()) {
                case 1:
                    continueApp = SignUpController.startRegistration();
                    break;
                case 2:
                    continueApp = LoginController.startLogin();
                    break;
                case 3:
                    System.out.println("Thank you for visiting today!");
                    System.exit(1);
                    break;
                default:
                    System.out.println("Enter a Valid Option!");
            }
        }
    }

    private int displayMainOptions() {
        System.out.println("+================================+");
        System.out.println("| Welcome to CommandLine Ecommerce Application!");
        System.out.println("|1. Sign Up |");
        System.out.println("|2. Login |");
        System.out.println("|3. Exit app. |");
        System.out.println("+================================+");

        String choiceEntered;
        System.out.println("Enter Choice (1, 2, 3) : ");
        choiceEntered = consoleScan.nextLine();
        try {
            return Integer.parseInt(choiceEntered);
        } catch (NumberFormatException ex) {
            System.out.println("Enter a valid choice (1, 2, 3). Try Again!");
            return 0;
        }
    }
}
