package com.fsd.service;

import com.fsd.exception.OrderException;
import com.fsd.model.Product;
import com.fsd.model.ProductFilter;
import com.fsd.model.PurchaseHistory;

import java.util.List;

public interface BuyerService {
    Product getProduct(int productId);

    List<Product> searchProducts();

    List<Product> searchProducts(ProductFilter filter);

    boolean buyProduct(int buyerId, int quantity, Product product) throws OrderException;

    List<PurchaseHistory> retrievePurchaseHistory(String buyerId);
}
