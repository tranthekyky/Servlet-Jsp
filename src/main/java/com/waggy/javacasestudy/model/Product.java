package com.waggy.javacasestudy.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String imgLink ;
    private String description;
    private int categoryId;

    public Product(int id, String name, double price, int quantity, String imgLink, String description , int categoryId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgLink = imgLink;
        this.description = description;
        this.categoryId = categoryId;
    }

    public Product(String name, double price, int quantity, String imgLink, String description, int categoryId) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgLink = imgLink;
        this.description = description;
        this.categoryId = categoryId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getImgLink() {
        return imgLink;
    }

    public void setImgLink(String imgLink) {
        this.imgLink = imgLink;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public int getCategoryId() {
        return categoryId;
    }
    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", imgLink='" + imgLink + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
