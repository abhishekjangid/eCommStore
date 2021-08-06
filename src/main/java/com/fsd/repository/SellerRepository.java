package com.fsd.repository;

import com.fsd.connection.ConnectionFactory;
import com.fsd.model.Product;
import com.fsd.utils.SQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SellerRepository {

    public void addProduct(Product product) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.INSERT_PRODUCT);
            statement.setString(1, product.getProductName());
            statement.setInt(2, product.getMakeYear());
            statement.setString(3, product.getCategory());
            statement.setString(4, product.getModelNumber());
            statement.setFloat(5, product.getPrice());
            statement.setString(6, product.getSellerId());
            statement.setInt(7, product.getCurrentNumbersStock());
            statement.setString(8, product.getSpecifications());
            statement.setString(9, product.getRemarks());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException("Exception occurred while adding product", e);
        }
    }

    public int updateQuantity(Product product) {
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.UPDATE_PRODUCT_QUANTITY);
            statement.setInt(1, product.getCurrentNumbersStock());
            statement.setInt(2, product.getId());
            return statement.executeUpdate();
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
    }

    public int updatePrice(Product product) {
        try (Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.UPDATE_PRODUCT_PRICE);
            statement.setFloat(1, product.getPrice());
            statement.setInt(2, product.getId());
            return statement.executeUpdate();
        } catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
    }

    public Product getProduct(int productId) {
        Product product = null;
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.GET_PRODUCT_DETAILS);
            statement.setInt(1, productId);
            ResultSet rs = statement.executeQuery();
            while(rs.next()){
                product = new Product();
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
            }
        } catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
        return product;
    }

    public List<Product> getProducts(String sellerId){
        List<Product> productList =  new ArrayList<>();
        try(Connection conn = ConnectionFactory.getInstance().getConnection()) {
            PreparedStatement statement = conn.prepareStatement(SQL.GET_PRODUCTS_FOR_SELLER_ID);
            statement.setString(1, sellerId);
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

                productList.add(product);
            }
        }catch (Exception e){
            throw new RuntimeException("Exception occurred while adding product", e);
        }
        return productList;
    }

}
