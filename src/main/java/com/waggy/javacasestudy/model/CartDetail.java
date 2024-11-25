package com.waggy.javacasestudy.model;

public class CartDetail {
    private int idCart ;
    private int idProduct ;
    private int quantity ;

    public CartDetail(int idCart , int idProduct ) {
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = 1;
    }

    public CartDetail(int idCart, int idProduct, int quantity) {
        this.idCart = idCart;
        this.idProduct = idProduct;
        this.quantity = quantity;
    }

    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity() {
        this.quantity++;
    }

    public int getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(int idProduct) {
        this.idProduct = idProduct;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
