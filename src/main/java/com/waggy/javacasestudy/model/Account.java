package com.waggy.javacasestudy.model;

public class Account {
    private int idAcc;
    private String username;
    private String password;
    private int isSeller;

    public Account(int idAcc, String username, String password, int isSeller) {
        this.idAcc = idAcc;
        this.username = username;
        this.password = password;
        this.isSeller = isSeller;
    }

    public int getIdAcc() {
        return idAcc;
    }

    public void setIdAcc(int idAcc) {
        this.idAcc = idAcc;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getIsSeller() {
        return isSeller;
    }

    public void setIsSeller(int isSeller) {
        this.isSeller = isSeller;
    }

    @Override
    public String toString() {
        return "Account{" +
                "idAcc=" + idAcc +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", isSeller=" + isSeller +
                '}';
    }
}
