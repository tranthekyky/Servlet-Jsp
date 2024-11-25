package com.waggy.javacasestudy.model;

public class Category {
    private int idC;
    private String nameC;

    public Category(int idC, String nameC) {
        this.idC = idC;
        this.nameC = nameC;
    }

    public int getIdC() {
        return idC;
    }

    public void setIdC(int idC) {
        this.idC = idC;
    }

    public String getNameC() {
        return nameC;
    }

    public void setNameC(String nameC) {
        this.nameC = nameC;
    }

    @Override
    public String toString() {
        return "CategoryProduct{" +
                "idC=" + idC +
                ", nameC='" + nameC + '\'' +
                '}';
    }
}
