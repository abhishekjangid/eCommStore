package com.fsd.service.impl;

import com.fsd.exception.OrderException;
import com.fsd.model.Product;
import com.fsd.model.ProductFilter;
import com.fsd.model.PurchaseHistory;
import com.fsd.repository.BuyerRepository;
import com.fsd.repository.SellerRepository;
import com.fsd.service.BuyerService;

import java.util.List;
import java.util.stream.Collectors;

public class BuyerServiceImpl implements BuyerService {
    BuyerRepository repository = new BuyerRepository();
    SellerRepository productRepository = new SellerRepository();

    @Override
    public Product getProduct(int productId) {
        return productRepository.getProduct(productId);
    }

    @Override
    public List<Product> searchProducts() {
        return repository.getAllProducts();
    }

    @Override
    public List<Product> searchProducts(ProductFilter filter) {
        return this.searchProducts().parallelStream()
                .filter(product -> filter.getProductName() == null || product.getProductName().contains(filter.getProductName()))
                .filter(product -> filter.getMinPrice() == 0.0F || product.getPrice() >= filter.getMinPrice())
                .filter(product -> filter.getMaxPrice() == 0.0F || product.getPrice() <= filter.getMaxPrice())
                .filter(product -> filter.getMakeYear() == 0 || filter.getMakeYear() == product.getMakeYear())
                .filter(product -> filter.getModelNumber() == null || product.getModelNumber().contains(filter.getModelNumber()))
                .filter(product -> filter.getCategory() == null || product.getCategory().contains(filter.getCategory()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean buyProduct(int buyerId, int quantity, Product product) throws OrderException {
        product.setCurrentNumbersStock(quantity);
        repository.updateProductQuantity(product);
        if(repository.createPurchaseHistory(buyerId, product) == 0){
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public List<PurchaseHistory> retrievePurchaseHistory(String buyerId) {
        return repository.getPurchaseHistory(buyerId);
    }
}
