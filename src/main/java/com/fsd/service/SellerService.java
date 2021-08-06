package com.fsd.service;

import com.fsd.model.Product;

import java.util.List;

public interface SellerService {
    void addProduct(Product product);

    int updateQuantity(Product product);

    int updatePrice(Product product);

    List<Product> getProducts(String SellerId);

    Product getProduct(int productId);
}
