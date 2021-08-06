package com.fsd.controller;

import com.fsd.model.Product;
import com.fsd.model.SecurityContext;
import com.fsd.service.SellerService;
import com.fsd.service.impl.SellerServiceImpl;

import java.util.List;
import java.util.Scanner;

public class SellerController {
    Scanner consoleScan;
    SellerService service;

    public SellerController() {
        this.consoleScan = new Scanner(System.in);
        this.service = new SellerServiceImpl();
    }

    public static void start(SecurityContext context) {
        boolean continueApp = true;
        SellerController controller = new SellerController();
        while(continueApp) {
            switch (controller.displaySellingOptions()) {
                case 1:
                    controller.startSell(context.getId());
                    break;
                case 2:
                    controller.viewProducts(context.getId());
                    break;
                case 3:
                    controller.viewSpecificProduct();
                    break;
                case 4:
                    controller.UpdatePrice();
                    break;
                case 5:
                    controller.UpdateStock();
                    break;
                case 6:
                    System.out.println("Thank you for visiting today!");
                    new MainController().start();
                    break;
                default:
                    System.out.println("Enter a Valid Option!");
            }
        }
    }

    private int displaySellingOptions() {
        System.out.println("+================================+");
        System.out.println("|1. Sell Product |");
        System.out.println("|2. View Products |");
        System.out.println("|3. View specific Product |");
        System.out.println("|4. Update Price |");
        System.out.println("|5. Update Quantity |");
        System.out.println("|6. Logout. |");
        System.out.println("+================================+");

        String choiceEntered;
        System.out.println("Enter Choice (1, 2, 3, 4, 5, 6) : ");
        choiceEntered = consoleScan.nextLine();
        try {
            return Integer.parseInt(choiceEntered);
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    private void startSell(String ID) {
        System.out.println("+================================+");
        System.out.println("| What you want to sell today?");
        System.out.println("+================================+");

        Product product =  new Product();
        System.out.println("Enter Product Details you want to sell:");
        System.out.println("Product Name : ");
        String productName = consoleScan.nextLine();
        product.setProductName(productName);
        System.out.println("Make Year : ");
        String makeYear = consoleScan.nextLine();
        product.setMakeYear(Integer.parseInt(makeYear));
        System.out.println("Model Number : ");
        String modelNumber = consoleScan.nextLine();
        product.setModelNumber(modelNumber);
        System.out.println("Category : ");
        String category = consoleScan.nextLine();
        product.setCategory(category);
        System.out.println("Price : ");
        String price = consoleScan.nextLine();
        product.setPrice(Float.parseFloat(price));
        product.setSellerId(ID);
        System.out.println("Quantity available to sell : ");
        String quantity = consoleScan.nextLine();
        product.setCurrentNumbersStock(Integer.parseInt(quantity));
        System.out.println("Specifications : ");
        String specifications = consoleScan.nextLine();
        product.setSpecifications(specifications);
        System.out.println("Remarks : ");
        String remarks = consoleScan.nextLine();
        product.setRemarks(remarks);

        service.addProduct(product);
    }

    private void viewProducts(String sellerId) {
        System.out.println("+================================+");
        System.out.println("| Product Details");
        System.out.println("+================================+");

        List<Product> products = service.getProducts(sellerId);
        for(Product product : products) {
            System.out.println(product);
        }
        System.out.println("+================================+");
    }

    private void viewSpecificProduct() {
        System.out.println("Enter Product ID you want to view:");
        final String ID = consoleScan.nextLine();
        System.out.println("+================================+");
        System.out.println("| Product Detail");
        System.out.println("+================================+");

        Product product = service.getProduct(Integer.parseInt(ID));
        if(product != null) {
            System.out.println(product);
        } else {
            System.out.println("| Product not found: ID: " + ID);
        }
        System.out.println("+================================+");
    }

    private void UpdateStock() {
        System.out.println("Enter Product ID you want to update:");
        final String ID = consoleScan.nextLine();
        System.out.println("+================================+");
        System.out.println("| Product Detail");
        System.out.println("+================================+");

        Product product = service.getProduct(Integer.parseInt(ID));
        if(product != null) {
            System.out.println(product);
            System.out.println("+================================+");
            System.out.println("Enter Stock Quantity you want to update:");
            final String stock = consoleScan.nextLine();
            product.setCurrentNumbersStock(Integer.parseInt(stock));
            if(service.updateQuantity(product) == 0){
                System.out.println("| Update failed: ID: " + ID);
            } else {
                System.out.println("| Update Successful: ID: " + ID);
            }
        } else {
            System.out.println("| Product not found: ID: " + ID);
        }
        System.out.println("+================================+");
    }

    private void UpdatePrice() {
        System.out.println("Enter Product ID you want to update:");
        final String ID = consoleScan.nextLine();
        System.out.println("+================================+");
        System.out.println("| Product Detail");
        System.out.println("+================================+");

        Product product = service.getProduct(Integer.parseInt(ID));
        if(product != null) {
            System.out.println(product);
            System.out.println("+================================+");
            System.out.println("Enter Price you want to update:");
            final String price = consoleScan.nextLine();
            product.setPrice(Float.parseFloat(price));
            if(service.updatePrice(product) == 0){
                System.out.println("| Update failed: ID: " + ID);
            } else {
                System.out.println("| Update Successful: ID: " + ID);
            }
        } else {
            System.out.println("| Product not found: ID: " + ID);
        }
        System.out.println("+================================+");
    }

}
