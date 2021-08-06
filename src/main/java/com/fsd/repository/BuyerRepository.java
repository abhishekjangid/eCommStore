package com.fsd.repository;

import com.fsd.connection.ConnectionFactory;
import com.fsd.exception.OrderException;
import com.fsd.model.Product;
import com.fsd.model.PurchaseHistory;
import com.fsd.utils.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BuyerRepository {
    SellerRepository sellerRepository = new SellerRepository();

    public List<Product> getAllProducts(){
        List<Product> allProducts = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.GET_ALL_PRODUCT);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                Product product = new Product();
                product.setId(rs.getInt("ID"));
                product.setProductName(rs.getString("NAME"));
                product.setCategory(rs.getString("CATEGORY"));
                product.setMakeYear(rs.getInt("MAKE_YEAR"));
                product.setModelNumber(rs.getString("MODEL_NUMBER"));
                product.setPrice(rs.getFloat("PRICE"));
                product.setSellerId(rs.getString("SELLER_ID"));
                product.setCurrentNumbersStock(rs.getInt("QUANTITY"));
                product.setSpecifications(rs.getString("SPECS"));
                product.setRemarks(rs.getString("REMARKS"));
                allProducts.add(product);
            }
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
        return allProducts;
    }

    public void updateProductQuantity(Product order) throws OrderException {

        Product stockAvailble = sellerRepository.getProduct(order.getId());
        if(stockAvailble.getCurrentNumbersStock() < order.getCurrentNumbersStock()){
            throw new OrderException("outofStock", "Insufficient stock");
        }
        stockAvailble.setCurrentNumbersStock(stockAvailble.getCurrentNumbersStock() - order.getCurrentNumbersStock());
        sellerRepository.updateQuantity(stockAvailble);
    }

    public int createPurchaseHistory(int buyerId, Product product){
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.INSERT_PURCHASE_HISTORY);
            statement.setInt(1, product.getId());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, buyerId);
            statement.setInt(4, product.getCurrentNumbersStock());
            return statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while adding product", e);
        }
    }

    public List<PurchaseHistory> getPurchaseHistory(String buyerId){
        List<PurchaseHistory> orderHistory = new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.GET_PURCHASE_HISTORY);
            statement.setString(1, buyerId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                PurchaseHistory order = new PurchaseHistory();
                order.setId(rs.getInt("ID"));
                order.setBuyerId(rs.getInt("BUYER_ID"));
                order.setProductId(rs.getInt("PRODUCT_ID"));
                order.setPrice(rs.getFloat("PRICE"));
                order.setQuantity(rs.getInt("QUANTITY"));
                order.setPurchaseDate(rs.getDate("ORDER_DATE_TIME"));
                orderHistory.add(order);
            }
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
        return orderHistory;
    }
}
