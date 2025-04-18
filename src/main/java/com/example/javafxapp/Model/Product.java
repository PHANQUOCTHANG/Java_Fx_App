package com.example.javafxapp.Model;

public class Product {
    private int product_id;
    private String product_name;
    private String description;
    private double price;
    private int category_id;
    private String imgSrc;
    private boolean status ;

    private boolean deleted = false ;


    public Product() {
    }


    public Product(String product_name, String description, double price, int category_id, String imgSrc, boolean status) {
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.imgSrc = imgSrc;
        this.status = status;
    }

    public Product(int product_id, String product_name, String description, double price, int category_id, String imgSrc, boolean status, boolean deleted) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.description = description;
        this.price = price;
        this.category_id = category_id;
        this.imgSrc = imgSrc;
        this.status = status;
        this.deleted = deleted;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category_id=" + category_id +
                ", imgSrc='" + imgSrc + '\'' +
                ", deleted=" + deleted +
                '}';
    }
}
