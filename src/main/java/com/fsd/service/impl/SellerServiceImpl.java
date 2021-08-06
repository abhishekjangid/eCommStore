package com.fsd.service.impl;

import com.fsd.model.Product;
import com.fsd.repository.SellerRepository;
import com.fsd.service.SellerService;

import java.util.List;

public class SellerServiceImpl implements SellerService {

    SellerRepository repository = new SellerRepository();

    public SellerServiceImpl() {
        this.repository = new SellerRepository();
    }


    @Override
    public void addProduct(Product product) {
        repository.addProduct(product);
    }

    @Override
    public void updateQuantity(int productId, int quantity) {

    }

    @Override
    public void updatePrice(int productId, float price) {

    }

    @Override
    public List<Product> getProducts(String sellerId) {
        return repository.getProducts(sellerId);
    }

    @Override
    public Product getProduct(int productId) {
        return null;
    }
}
