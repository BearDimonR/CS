package com.miedviediev.cs.Homework2.Models;

import java.util.ArrayList;
public class Storage {

    private static Storage storage;

    public static Storage getInstance() {
        if(storage == null)
            storage = new Storage();
        return storage;
    }

    private ArrayList<GroupProduct> groupProducts;
    private ArrayList<Product> products;

    private Storage() {
        groupProducts = new ArrayList<>(5);
        products = new ArrayList<>(10);

        groupProducts.add(new GroupProduct("Eggs", "Really cool eggs"));
        groupProducts.add(new GroupProduct("Bread", "The best bread in this world"));
        groupProducts.add(new GroupProduct("Drinks", "You will die from this"));

        products.add(new Product("Soft eggs", "Try it", 20, 100));
        products.add(new Product("Solid eggs", "Don't try it", 50, 40));
        products.add(new Product("Hard eggs", "Just try it", 15, 300, 1));
        products.add(new Product("White bread", "Tasty", 10, 500, 2));
        products.add(new Product("Dark bread", "Not so tasty", 15, 300));
        products.add(new Product("Sweat bread", "Kaef", 25, 200, 2));
        products.add(new Product("Small bread", "I want more", 5, 600, 2));
        products.add(new Product("Tequila", "Tequilaaaaa!!!!", 100, 235));
        products.add(new Product("Rom", "Good choice for company", 95, 432, 3));
        products.add(new Product("Gorilka", "Slava Ukraini", 50, 786, 3));

    }

    public Product getProductById(int productId) throws NullPointerException {
        return products.get(productId);
      //  return products.stream().filter(x -> x.getId() == productId).findFirst().orElseThrow(null);
    }

    public GroupProduct getGroupById(int groupId) throws NullPointerException {
        return groupProducts.get(groupId);
      //  return groupProducts.stream().filter(x -> x.getId() == groupId).findFirst().orElseThrow(null);
    }

    public String getAmount(int productId) throws NullPointerException {
        return String.valueOf(getProductById(productId).getAmount());
    }

    public String getPrice(int productId) throws NullPointerException {
        return String.valueOf(getProductById(productId).getPrice());
    }

    public String getGroupId(int productId) throws NullPointerException {
        return String.valueOf(getProductById(productId).getGroupProduct());
    }

    public String removeAmount(int productId, int removeAmount) throws NullPointerException  {
        Product p = getProductById(productId);
        products.get(products.indexOf(p)).setAmount(p.getAmount() - removeAmount);
        return "Ok";
    }

    public String addAmount(int productId, int addAmount) throws NullPointerException  {
        Product p = getProductById(productId);
        products.get(products.indexOf(p)).setAmount(p.getAmount() + addAmount);
        return "Ok";
    }

    public String addGroup(String name, String description) {
        groupProducts.add(new GroupProduct(name, description));
        return "Ok";
    }

    public String setPrice(int productId, int newPrice) throws NullPointerException {
        getProductById(productId).setPrice(newPrice);
        return "Ok";
    }

    public String setGroup(int productId, int groupId) throws NullPointerException {
        getProductById(productId).setGroupProduct(groupId);
        return "Ok";
    }

    public ArrayList<GroupProduct> getGroupProducts() {
        return groupProducts;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Storage{" +
                "groupProducts=\n" + groupProducts +
                "\n\n, products=" + products +
                '}';
    }
}
