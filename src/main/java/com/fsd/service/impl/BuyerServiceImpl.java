package com.fsd.service.impl;

import com.fsd.model.Product;
import com.fsd.model.ProductFilter;
import com.fsd.model.PurchaseHistory;
import com.fsd.service.BuyerService;

import java.util.List;

public class BuyerServiceImpl implements BuyerService {
    @Override
    public List<Product> searchProducts(ProductFilter filter) {
        return null;
    }

    @Override
    public boolean buyProduct(int productId, int quantity) {
        return false;
    }

    @Override
    public List<PurchaseHistory> retrievePurchaseHistory(int buyerId) {
        return null;
    }
}
