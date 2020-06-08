package com.miedviediev.cs.Homework2.Models;

import java.util.Objects;

public class Product {
    public static long counter = 0;

    private long id;
    private String name;
    private String description;
    private float price;
    private float amount;
    private long groupProductId;

    public Product() {}

    public Product(String name, String description, float price, float amount) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        id = counter++;
    }

    public Product(String name, String description, float price, float amount, long groupProduct) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.groupProductId = groupProduct;
        id = counter++;
    }

    public Product(long id, String name, float price, float amount, long groupProductId, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.amount = amount;
        this.groupProductId = groupProductId;
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

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public long getGroupProduct() {
        return groupProductId;
    }

    public void setGroupProduct(long groupProductId) {
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
