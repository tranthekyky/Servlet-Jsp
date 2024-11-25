package com.waggy.javacasestudy.service.product;

import com.waggy.javacasestudy.model.Product;
import com.waggy.javacasestudy.service.iservice.IServiceProduct;
import com.waggy.javacasestudy.service.connect.ConnectSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IServiceProduct<Product> {
    private Connection connection = ConnectSQL.getConnectData();
    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product";
        try  {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
               products.add(new Product(
                       resultSet.getInt(1),
                       resultSet.getString(2),
                       resultSet.getDouble(3),
                       resultSet.getInt(4) ,
                       resultSet.getString(5),
                       resultSet.getString(6) ,
                       resultSet.getInt(7)
               ));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return products ;
    }

    @Override
    public Product getProductById(int id) {
        String sql = "SELECT * FROM product WHERE idProduct = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4) ,
                        resultSet.getString(5),
                        resultSet.getString(6) ,
                        resultSet.getInt(7)
                );
                return product;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<Product> getProductByPrice(double minPrice , double maxPrice) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE priceProduct between ? and ? ";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setDouble(1, minPrice);
            preparedStatement.setDouble(2, maxPrice);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4) ,
                        resultSet.getString(5),
                        resultSet.getString(6) ,
                        resultSet.getInt(7)
                );
                products.add(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    @Override
    public boolean addProduct(Product product) {
        String sql = "insert into product ( nameProduct, priceProduct, quantityProduct, linkImage, describeProduct, categoryId)\n" +
                "values (? ,? ,? ,? ,? ,?) ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getImgLink());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getCategoryId());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void deleteProduct(int id) {
        String sql = "delete from product where idProduct = ? ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateProduct(int id, Product product) {
        String sql = "UPDATE product SET nameProduct = ?, priceProduct= ?,quantityProduct = ? ,linkImage = ?,describeProduct = ?, categoryId = ? WHERE idProduct = ?;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,product.getName());
            preparedStatement.setDouble(2,product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getImgLink());
            preparedStatement.setString(5,product.getDescription());
            preparedStatement.setInt(6,product.getCategoryId());
            preparedStatement.setInt(7,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
    public List<Product> searchProductByName(String name) {
        String sql = "SELECT * FROM product WHERE nameProduct like ?;";
        List<Product> products = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,"%"+ name+"%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product pro = new Product( resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getInt(7)
                        );
                products.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public List<Product> getProductsByCategory(int categoryId) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM product WHERE categoryId = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, categoryId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                products.add(new Product(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getDouble(3),
                        resultSet.getInt(4) ,
                        resultSet.getString(5),
                        resultSet.getString(6) ,
                        resultSet.getInt(7)
                ));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return products;
    }

    public static void main(String[] args) {
        ProductService productService = new ProductService();
        List<Product> products = productService.searchProductByName("Áo");
        for (Product product : products) {
            System.out.println(product);
        }
    }
}
