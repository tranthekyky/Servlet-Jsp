package com.waggy.javacasestudy.model;

public class Cart {
    private int idCart ;
    private int idUser ;
    private boolean status ;

    public Cart(int idCart, int idUser ) {
        this.idCart = idCart;
        this.idUser = idUser;
        this.status = false;
    }
    public Cart( int idUser ) {
        this.idUser = idUser;
    }


    public int getIdCart() {
        return idCart;
    }

    public void setIdCart(int idCart) {
        this.idCart = idCart;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}

