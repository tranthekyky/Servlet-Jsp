package com.waggy.javacasestudy.service.login;

import com.waggy.javacasestudy.model.Account;
import com.waggy.javacasestudy.service.connect.ConnectSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginService {
    private Connection connection = ConnectSQL.getConnectData();
    public Account getAccount(String username, String password) {
        String sql ="select * from account where nameAcc = ? and passAcc = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return new Account(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getInt(4)
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
    public void createAccount(String username, String password) {
        String sql = "insert into account(nameAcc, passAcc, isSeller) values(?,? ,0);";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isCheckNameDuplicate (String username) {
            String sql = "select count(idAcc) from account where nameAcc = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 0) {
                    return true;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    public static void main(String[] args) {
         LoginService loginService = new LoginService();
        System.out.println(loginService.isCheckNameDuplicate("admin1"));;
    }
}
