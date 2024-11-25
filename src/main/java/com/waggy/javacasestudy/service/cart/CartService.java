package com.waggy.javacasestudy.service.cart;

import com.waggy.javacasestudy.model.Cart;
import com.waggy.javacasestudy.model.CartDetail;
import com.waggy.javacasestudy.service.connect.ConnectSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CartService {
    private Connection connection = ConnectSQL.getConnectData();

    public int getIdCartByUserId(int userId) {
        String sql = "select idCart from cart where idUser = ? and status = 0 ;";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, userId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                return resultSet.getInt(1);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public ArrayList<CartDetail> getAllProductInCart (int idCart) {
        ArrayList<CartDetail> cartDetailArrayList = new ArrayList<>();
        String sql = "select d.idCart ,d.idProduct,d.quantityProduct from cart_detail d\n" +
                "join Waggy.cart c on d.idCart = c.idCart\n" +
                " where d.idCart = ? and c.status = 0 ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CartDetail cartDetail = new CartDetail(
                        resultSet.getInt(1) ,
                        resultSet.getInt(2) ,
                        resultSet.getInt(3)
                        );
                cartDetailArrayList.add(cartDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cartDetailArrayList;
    }
    public boolean isCheckStatusCart(int idCart) {
        String sql = "select status from cart where idCart = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 0) {
                    return true ;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public boolean isCheckProductInCart(int idCart, int productId) {
        String sql = " select idCart from cart_detail where idProduct = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, productId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == idCart) {
                    return true ;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    public void createCart(int idUser) {
        String sql = "insert into cart (idUser) value (?) ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idUser);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateQuantity(int idCart, int productId) {
        String sql = " update cart_detail \n" +
                " set quantityProduct = quantityProduct + 1\n" +
                " where idCart = ? and idProduct = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void updateQuantityInCart(int idCart, int productId , int quantity) {
        String sql = " update cart_detail \n" +
                " set quantityProduct = ?\n" +
                " where idCart = ? and idProduct = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, quantity);
            preparedStatement.setInt(2, idCart);
            preparedStatement.setInt(3, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void addProductInCart(int idCart, int productId) {
        String sql = "insert into cart_detail (idCart , idProduct , quantityProduct) value (? , ? , 1);" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void deleteProductInCart(int idCart, int productId) {
        String sql = "delete from cart_detail where idCart = ? and idProduct = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            preparedStatement.setInt(2, productId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void payProductInCart(int idCart) {
        String sql = "update cart\n" +
                "set status = true where idCart = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public boolean isCheckCartNotNull(int idCart) {
        String sql = "select count(idProduct) from cart_detail where idCart = ? ;" ;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, idCart);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (resultSet.getInt(1) == 0) {
                    return false;
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return true;
    }

    public static void main(String[] args) {
        CartService cartService = new CartService();
    }

}
