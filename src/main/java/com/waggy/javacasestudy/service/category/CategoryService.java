package com.waggy.javacasestudy.service.category;

import com.waggy.javacasestudy.model.Category;
import com.waggy.javacasestudy.service.connect.ConnectSQL;
import com.waggy.javacasestudy.service.iservice.IServiceCategory;
import com.waggy.javacasestudy.service.iservice.IServiceProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements IServiceCategory<Category> {
    private Connection connection = ConnectSQL.getConnectData() ;

    @Override
    public List<Category> getAllCategory() {
        String sql = "select * from category";
        List<Category> categoryProducts = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category cate = new Category(resultSet.getInt(1), resultSet.getString(2));
                categoryProducts.add(cate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return categoryProducts;
    }

    @Override
    public Category getCategoryById(int id) {
        String sql = "select * from category where idC = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category cate = new Category(resultSet.getInt(1), resultSet.getString(2));
                return cate;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


}
