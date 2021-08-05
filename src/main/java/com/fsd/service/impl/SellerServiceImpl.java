package com.fsd.service.impl;

import com.fsd.model.Product;
import com.fsd.repository.SellerRepository;
import com.fsd.service.SellerService;

public class SellerServiceImpl implements SellerService {

    @Override
    public void addProduct(Product product) {
        new SellerRepository().addProduct(product);
    }

    @Override
    public void updateQuantity(int productId, int quantity) {

    }

    @Override
    public void updatePrice(int productId, float price) {

    }

    @Override
    public Product getProduct(int productId) {
        return null;
    }
}
