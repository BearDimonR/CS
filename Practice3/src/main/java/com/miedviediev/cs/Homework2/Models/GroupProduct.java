package com.miedviediev.cs.Homework2.Models;

import java.util.Objects;

public class GroupProduct {
    public static long counter = 0;

    private long id;
    private String name;
    private String description;

    public GroupProduct(String name){
        this.name = name;
        this.description = "";
        id = counter++;
    }

    public GroupProduct(String name, String description){
        this.name = name;
        this.description = description;
        id = counter++;
    }

    public GroupProduct(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public static long getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        GroupProduct.counter = counter;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupProduct)) return false;
        GroupProduct that = (GroupProduct) o;
        return getId() == that.getId() &&
                getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription());
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

    @Override
    public String toString() {
        return "GroupProduct{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ",\n description='" + description + '\'' +
                "}\n";
    }
}
