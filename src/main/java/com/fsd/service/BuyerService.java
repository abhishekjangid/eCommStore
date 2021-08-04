package com.fsd.service;

import com.fsd.model.Product;
import com.fsd.model.ProductFilter;
import com.fsd.model.PurchaseHistory;

import java.util.List;

public interface BuyerService {
    List<Product> searchProducts(ProductFilter filter);

    boolean buyProduct(int productId, int quantity);

    List<PurchaseHistory> retrievePurchaseHistory(int buyerId);
}
