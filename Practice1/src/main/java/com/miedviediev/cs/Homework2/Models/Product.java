package com.miedviediev.cs.Homework2.Models;

import java.util.Objects;

public class Product {
    public static int counter = 0;

    private int id;
    private String name;
    private String description;
    private double price;
    private double amount;

    private int groupProductId;

    public Product(String name, String description, double price, double amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        id = counter++;
    }

    public Product(String name, String description, double price, double amount, int groupProduct) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.groupProductId = groupProduct;
        id = counter++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return getId() == product.getId() &&
                Objects.equals(getName(), product.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getGroupProduct() {
        return groupProductId;
    }

    public void setGroupProduct(int groupProductId) {
        this.groupProductId = groupProductId;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", amount=" + amount +
                ",groupProductId=" + groupProductId +
                ",\n description='" + description + '\'' +
                "}\n";
    }
}
