package com.fsd.repository;

import com.fsd.connection.ConnectionFactory;
import com.fsd.model.Product;
import com.fsd.utils.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SellerRepository {

    public void addProduct(List<Product> products) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.INSERT_PRODUCT);
            for (Product product : products) {
                statement.setString(1, product.getProductName());
                statement.setString(2, product.getMakeYear());
                statement.setString(3, product.getModelNumber());
                statement.setFloat(4, product.getPrice());
                statement.setInt(5, product.getSellerId());
                statement.setInt(6, product.getCurrentNumbersStock());
                statement.setString(7, product.getSpecifications());
                statement.setString(8, product.getRemarks());
                statement.addBatch();
            }
            statement.executeBatch();
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
    }

    public void updateQuantity(Product product) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.UPDATE_PRODUCT_QUANTITY);
            statement.setInt(1, product.getCurrentNumbersStock());
            statement.setInt(2, product.getId());
            statement.executeBatch();
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
    }

    public void updatePrice(Product product) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.UPDATE_PRODUCT_QUANTITY);
            statement.setFloat(1, product.getPrice());
            statement.setInt(2, product.getId());
            statement.execute();
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
    }

    public Product getProduct(int productId){
        Product product = new Product();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.GET_PRODUCT_DETAILS);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                product.setId(rs.getInt("ID"));
                product.setProductName(rs.getString("NAME"));
                product.setMakeYear(rs.getString("MAKE_YEAR"));
                product.setModelNumber(rs.getString("MODEL_NUMBER"));
                product.setPrice(rs.getFloat("PRICE"));
                product.setSellerId(rs.getInt("SELLER_ID"));
                product.setCurrentNumbersStock(rs.getInt("CURRENT_STOCK"));
                product.setSpecifications(rs.getString("SPECS"));
                product.setRemarks(rs.getString("REMARKS"));
            }
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
        return product;
    }

}
