package ua.com.ke4a_store.entity;

import java.util.Objects;


public class Good {
    private long id;
    private String name;
    private int price;
    private int count;
    private long groupId;

    public Good(long id, String name, int price, int count, long groupId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.groupId = groupId;
    }


    public Good(String name, int price, int count){
        this.name=name;
        this.price=price;
        this.count=count;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Good good = (Good) o;
        return id == good.id &&
                price == good.price &&
                count == good.count &&
                Objects.equals(name, good.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, count);
    }

    @Override
    public String toString() {
        return "Good{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", count=" + count +
                '}';
    }
}
