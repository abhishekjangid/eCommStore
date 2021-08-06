package com.fsd.service;

import com.fsd.model.Product;

import java.util.List;

public interface SellerService {
    void addProduct(Product product);

    void updateQuantity(int productId, int quantity);

    void updatePrice(int productId, float price);

    List<Product> getProducts(String SellerId);

    Product getProduct(int productId);
}
