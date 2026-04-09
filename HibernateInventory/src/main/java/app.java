package com.inventory.app;

import com.inventory.dao.ProductDAO;
import com.inventory.entity.Product;

public class MainApp {

    public static void main(String[] args) {

        ProductDAO dao = new ProductDAO();

        // INSERT
        dao.addProduct(new Product("Laptop", "Gaming Laptop", 80000, 10));
        dao.addProduct(new Product("Mouse", "Wireless Mouse", 1200, 50));

        // READ
        Product p = dao.getProduct(1);
        System.out.println(p.getName() + " " + p.getPrice());

        // UPDATE
        dao.updateProduct(1, 78000, 8);

        // DELETE
        dao.deleteProduct(2);
    }
}
