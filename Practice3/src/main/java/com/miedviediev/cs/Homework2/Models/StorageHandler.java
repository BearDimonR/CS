package com.miedviediev.cs.Homework2.Models;

import com.miedviediev.cs.Database.GroupDAO;
import com.miedviediev.cs.Database.ProductDAO;

import java.security.InvalidParameterException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class StorageHandler {

    private static StorageHandler storage;

    public static StorageHandler getInstance() {
        if(storage == null)
            storage = new StorageHandler();
        return storage;
    }

    private StorageHandler() {}

    public Product getProductById(long productId) throws SQLException, InvalidParameterException  {
        Optional<Product> res = ProductDAO.getInstance().get(productId);
        if(res.isEmpty())
            throw new InvalidParameterException();
        else return res.get();
    }

    public GroupProduct getGroupById(long groupId) throws SQLException, InvalidParameterException {
        Optional<GroupProduct> res = GroupDAO.getInstance().get(groupId);
        if(res.isEmpty())
            throw new InvalidParameterException();
        else return res.get();
    }

    public String getAmount(long productId) throws SQLException, InvalidParameterException {
        Optional<Object> res = ProductDAO.getInstance().get(productId, "amount");
        if(res.isEmpty())
            throw new InvalidParameterException();
        return String.valueOf(res.get());
    }

    public String getPrice(long productId) throws SQLException, InvalidParameterException {
        Optional<Object> res = ProductDAO.getInstance().get(productId, "price");
        if(res.isEmpty())
            throw new InvalidParameterException();
        return String.valueOf(res.get());
    }

    public String getGroupId(long productId) throws SQLException, InvalidParameterException {
        Optional<Object> res = ProductDAO.getInstance().get(productId, "group_products_id");
        if(res.isEmpty())
            throw new InvalidParameterException();
        return String.valueOf(res.get());
    }

    public String removeAmount(long productId, float removeAmount) throws SQLException, InvalidParameterException  {
        ProductDAO.getInstance().update
                (productId, "amount = " + (Float.valueOf(getAmount(productId))-removeAmount));
        return "Ok";
    }

    public String addAmount(long productId, float addAmount) throws SQLException, InvalidParameterException  {
        ProductDAO.getInstance().update
                (productId, "amount = " + (Float.valueOf(getAmount(productId))+addAmount));
        return "Ok";
    }

    public String addGroup(String name, String description) throws SQLException, InvalidParameterException {
        if(!GroupDAO.getInstance().save(new GroupProduct(name, description)))
            throw new InvalidParameterException();
        return "Ok";
    }

    public String setPrice(long productId, long newPrice) throws SQLException, InvalidParameterException {
        ProductDAO.getInstance().update(productId, "price = " + newPrice);
        return "Ok";
    }

    public String setGroup(long productId, long groupId) throws SQLException, InvalidParameterException {
        ProductDAO.getInstance().update(productId, "group_products_id = " + groupId);
        return "Ok";
    }

    public List<GroupProduct> getGroupProducts() throws SQLException, InvalidParameterException {
        return GroupDAO.getInstance().getAll();
    }

    public List<Product> getProducts() throws SQLException, InvalidParameterException {
        return ProductDAO.getInstance().getAll();
    }
}

