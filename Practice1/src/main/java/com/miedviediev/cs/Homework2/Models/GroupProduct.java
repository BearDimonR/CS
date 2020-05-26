package com.miedviediev.cs.Homework2.Models;

import java.util.Objects;

public class GroupProduct {
    public static int counter = 0;

    private int id;
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

    public int getId() {
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
