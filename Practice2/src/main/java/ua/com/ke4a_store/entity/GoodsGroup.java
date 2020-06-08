package ua.com.ke4a_store.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GoodsGroup {
    private long id;
    private final String name;
    private List<Good> goods;

    public GoodsGroup(long id, String name) {
        this.id=id;
        this.name = name;
        goods=new ArrayList<>();
    }

    public GoodsGroup(String name) {
        this.name = name;
        goods=new ArrayList<>();
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

    public List<Good> getGoods() {
        return goods;
    }

    public void addAll(List<Good> goods){
        this.goods.addAll(goods);
    }

    public long getTotalPrice(){
        long res = 0;
        for (Good good : goods){
            res += good.getCount() * good.getPrice();
        }
        return res;
    }

    public long getTotalCount(){
        long res = 0;
        for (Good good: goods){
            res += good.getCount();
        }
        return res;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GoodsGroup that = (GoodsGroup) o;
        return id == that.id &&
                Objects.equals(name, that.name) &&
                Objects.equals(goods, that.goods);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, goods);
    }

    @Override
    public String toString() {
        return "GoodsGroup{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", goods=" + goods +
                '}';
    }
}
