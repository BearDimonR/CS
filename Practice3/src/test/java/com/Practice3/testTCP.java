package com.Practice3;

import com.miedviediev.cs.Database.GroupDAO;
import com.miedviediev.cs.Database.ProductDAO;
import com.miedviediev.cs.Homework2.Models.GroupProduct;
import com.miedviediev.cs.Homework2.Models.Product;
import org.junit.Assert;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class testTCP {

    @Test
    public void testGroupEdit() throws SQLException {
        GroupProduct g = GroupDAO.getInstance().get(1).get();
        Assert.assertTrue(GroupDAO.getInstance().update(1, "name = 'Cake'"));
        Assert.assertNotEquals(g.getName(), GroupDAO.getInstance().get(1).get().getName());
        Assert.assertTrue(GroupDAO.getInstance().update(1, "name = '" + g.getName() + "'"));
        Assert.assertEquals(g.getName(), GroupDAO.getInstance().get(1).get().getName());
    }

    @Test
    public void testProductEdit() throws SQLException {
        Product g = ProductDAO.getInstance().get(5).get();
        Assert.assertTrue(ProductDAO.getInstance().update(5,
                "name = 'TBREADX', price = " + (g.getPrice() + 20)));
        Assert.assertNotEquals(g.getName(), ProductDAO.getInstance().get(5).get().getName());
        Assert.assertTrue(ProductDAO.getInstance().update(5,
                "name = '" + g.getName() + "', price = " + (g.getPrice())));
        Assert.assertEquals(g.getName(), ProductDAO.getInstance().get(5).get().getName());
    }

    @Test
    public void testGetAllProduct() throws SQLException {
        List<Product> products = ProductDAO.getInstance().getAll();
        for (Product p: products) {
            System.out.println(p);
        }
        Assert.assertTrue(products.size() != 0);
    }

    @Test
    public void testGetAllGroup() throws SQLException {
        List<GroupProduct> groupProducts = GroupDAO.getInstance().getAll();
        for (GroupProduct g: groupProducts) {
            System.out.println(g);
        }
        Assert.assertTrue(groupProducts.size() != 0);
    }

    @Test
    public void testAddDeleteProduct() throws SQLException {
        Assert.assertTrue(ProductDAO.getInstance().save(new Product(
                "test",
                "dfdkf",
                20,
                20,
                1
                )));
        List<Product> products = ProductDAO.getInstance().getAll();
        Assert.assertTrue(ProductDAO.getInstance().delete(products.get(products.size() - 1).getId()));
    }

    @Test
    public void testAddDeleteGroup() throws SQLException {
        Assert.assertTrue(GroupDAO.getInstance().save(new GroupProduct(
                "test_group",
                "dfdkfasldjskjncncdjf"
        )));
        List<GroupProduct> groupProducts = GroupDAO.getInstance().getAll();
        Assert.assertTrue(GroupDAO.getInstance().delete(groupProducts.get(groupProducts.size() - 1).getId()));
    }

    @Test
    public void testGetAllByFieldGroup() throws SQLException {
        List<Object> objects = GroupDAO.getInstance().getAll("name");
        for (Object o: objects) {
            System.out.println(o);
        }
        Assert.assertTrue(objects.size() != 0);
    }

    @Test
    public void testGetAllByFieldProducts() throws SQLException {
        List<Object> objects = ProductDAO.getInstance().getAll("name");
        for (Object o: objects) {
            System.out.println(o);
        }
        Assert.assertTrue(objects.size() != 0);
    }
}
