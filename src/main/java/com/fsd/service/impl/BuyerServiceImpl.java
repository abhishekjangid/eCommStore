package com.fsd.service.impl;

import com.fsd.exception.OrderException;
import com.fsd.model.Product;
import com.fsd.model.ProductFilter;
import com.fsd.model.PurchaseHistory;
import com.fsd.repository.BuyerRepository;
import com.fsd.service.BuyerService;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerServiceImpl implements BuyerService {

    BuyerRepository repository = new BuyerRepository();

    @Override
    public List<Product> searchProducts(ProductFilter filter) {
        return repository.getAllProducts().parallelStream()
                .filter(product -> filter.getModelNumber().equals(product.getModelNumber()))
                .filter(product -> filter.getProductName().equals(product.getProductName()))
                .filter(product -> filter.getMakeYear().equals(product.getMakeYear()))
                .filter(product -> product.getPrice() >= filter.getMinPrice())
                .filter(product -> product.getPrice() <= filter.getMaxPrice())
                .collect(Collectors.toList());
    }

    @Override
    public boolean buyProduct(int productId, int quantity) throws OrderException {
        Product order = new Product();
        order.setCurrentNumbersStock(quantity);
        order.setId(productId);
        repository.updateProductQuantity(order);
        return Boolean.TRUE;
    }

    @Override
    public List<PurchaseHistory> retrievePurchaseHistory(int buyerId) {
        return repository.getPurchaseHistory(buyerId);
    }
}
