package com.fsd.controller;

import com.fsd.exception.OrderException;
import com.fsd.model.Product;
import com.fsd.model.ProductFilter;
import com.fsd.model.PurchaseHistory;
import com.fsd.model.SecurityContext;
import com.fsd.service.BuyerService;
import com.fsd.service.impl.BuyerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class BuyerController {
    Scanner consoleScan;
    BuyerService service;
    ProductFilter filter = new ProductFilter();

    public BuyerController() {
        this.consoleScan = new Scanner(System.in);
        this.service = new BuyerServiceImpl();
    }

    public static void start(SecurityContext context) {
        BuyerController controller = new BuyerController();
        while(true) {
            switch (controller.displayBuyersOptions()) {
                case 1:
                    controller.viewProducts(context.getId());
                    break;
                case 2:
                    controller.viewPurchaseHistory(context.getId());
                    break;
                case 3:
                    System.out.println("Thank you for visiting today!");
                    new MainController().start();
                    break;
                default:
                    System.out.println("Enter a Valid Option!");
            }
        }
    }

    private void startPurchase(String buyerId) {
        BuyerController controller = new BuyerController();
        switch (controller.displayPurchaseOptions()) {
            case 1:
                controller.startFilters(buyerId);
                break;
            case 2:
                controller.purchase(buyerId);
                break;
            case 3:
                System.out.println("Thank you for visiting today!");
                new MainController().start();
                break;
            default:
                System.out.println("Enter a Valid Option!");
        }
    }

    private void startFilters(String buyerId) {
        BuyerController controller = new BuyerController();

        switch (controller.displayFilterOptions()) {
            case 1:
                System.out.println("Enter a Product Name!");
                filter.setProductName(consoleScan.nextLine());
                service.searchProducts(filter);
                break;
            case 2:
                System.out.println("Enter a Category!");
                filter.setCategory(consoleScan.nextLine());
                service.searchProducts(filter);
                break;
            case 3:
                System.out.println("Enter a Minimum price!");
                filter.setMinPrice(Float.parseFloat(consoleScan.nextLine()));
                System.out.println("Enter a Maximum price!");
                filter.setMaxPrice(Float.parseFloat(consoleScan.nextLine()));
                service.searchProducts(filter);
                break;
            case 4:
                System.out.println("Enter a Make year!");
                filter.setMakeYear(Integer.parseInt(consoleScan.nextLine()));
                break;
            case 5:
                System.out.println("Enter a Model Number!");
                filter.setModelNumber(consoleScan.nextLine());
                break;
            default:
                System.out.println("Enter a Valid Option!");
        }
        viewFilteredProducts(buyerId);
    }

    private int displayBuyersOptions() {
        System.out.println("+================================+");
        System.out.println("|1. View Products |");
        System.out.println("|2. View purchase history |");
        System.out.println("|3. Logout. |");
        System.out.println("+================================+");

        String choiceEntered;
        System.out.println("Enter Choice (1, 2, 3) : ");
        choiceEntered = consoleScan.nextLine();
        try {
            return Integer.parseInt(choiceEntered);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private int displayFilterOptions() {
        System.out.println("+================================+");
        System.out.println("| Choose Filter |");
        System.out.println("|1. Product Name |");
        System.out.println("|2. Category |");
        System.out.println("|3. Price |");
        System.out.println("|4. Make year |");
        System.out.println("|5. Model Number |");
        System.out.println("+================================+");

        String choiceEntered;
        System.out.println("Enter Choice (1, 2, 3, 4) : ");
        choiceEntered = consoleScan.nextLine();
        try {
            return Integer.parseInt(choiceEntered);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void viewProducts(String buyerId) {
        System.out.println("+================================+");
        System.out.println("| Product Details");
        System.out.println("+================================+");

        List<Product> products = service.searchProducts();
        if (products.size() > 0) {
            for (Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("| No products available at this moment!");
        }
        System.out.println("+================================+");
        startPurchase(buyerId);
    }

    private void viewFilteredProducts(String buyerId) {
        System.out.println("+================================+");
        System.out.println("| Product Details" + filter);
        System.out.println("+================================+");


        List<Product> products = service.searchProducts(filter);
        if (products.size() > 0) {
            for(Product product : products) {
                System.out.println(product);
            }
        } else {
            System.out.println("| No products available for this filter!");
        }
        System.out.println("+================================+");
        startPurchase(buyerId);
    }

    private int displayPurchaseOptions() {
        System.out.println("+================================+");
        System.out.println("|1. Filter Products |");
        System.out.println("|2. Buy Product |");
        System.out.println("|3. Logout. |");
        System.out.println("+================================+");

        String choiceEntered;
        System.out.println("Enter Choice (1, 2, 3) : ");
        choiceEntered = consoleScan.nextLine();
        try {
            return Integer.parseInt(choiceEntered);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void purchase(String buyerId) {
        System.out.println("+================================+");
        System.out.println("| Thank you for choosing us for you shopping");
        System.out.println("+================================+");
        System.out.println("Enter Product ID you want to purchase:");
        final String ID = consoleScan.nextLine();
        Product product = service.getProduct(Integer.parseInt(ID));
        if(product != null) {
            System.out.println(product);
            System.out.println("+================================+");
            System.out.println("Enter Quantity you want to purchase:");
            final String QUANTITY = consoleScan.nextLine();
            try {
                if(service.buyProduct(Integer.parseInt(buyerId), Integer.parseInt(QUANTITY), product)){
                    System.out.println("| Order Successful. Product ID: " + ID);
                } else {
                    System.out.println("| Order failed. Product ID: " + ID);
                }
            } catch (OrderException e) {
                System.out.println("| Order failed - " + e.getMessage() + " Product ID: " + ID);
            }
        } else {
            System.out.println("| Product not found: ID: " + ID);
        }
    }

    private void viewPurchaseHistory(String buyerId) {
        System.out.println("+================================+");
        System.out.println("| Purchase History Details");
        System.out.println("+================================+");

        List<PurchaseHistory> purchaseHistories = service.retrievePurchaseHistory(buyerId);
        if(purchaseHistories.size() > 0) {
            for(PurchaseHistory history : purchaseHistories) {
                System.out.println(history);
            }
        } else {
            System.out.println("| You haven't made any purchase yet. Start shopping!");
        }
        System.out.println("+================================+");
    }

}
