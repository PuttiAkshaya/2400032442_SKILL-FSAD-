package com.retail;

import com.retail.entity.Product;
import com.retail.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class MainApp {

    public static void main(String[] args) {

        insertProducts();
        getProductById(1L);
        updateProduct(1L, 95000, 20);
        deleteProduct(2L);

        HibernateUtil.shutdown();
    }

    // INSERT MULTIPLE PRODUCTS
    public static void insertProducts() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product p1 = new Product("Laptop", "Gaming Laptop", 85000, 10);
        Product p2 = new Product("Mobile", "Smartphone", 25000, 30);
        Product p3 = new Product("Headphones", "Wireless Headphones", 3000, 50);

        session.persist(p1);
        session.persist(p2);
        session.persist(p3);

        tx.commit();
        session.close();

        System.out.println("Products Inserted Successfully");
    }

    // RETRIEVE PRODUCT BY ID
    public static void getProductById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();

        Product product = session.get(Product.class, id);

        if (product != null) {
            System.out.println("Product Found:");
            System.out.println("Name: " + product.getName());
            System.out.println("Price: " + product.getPrice());
            System.out.println("Quantity: " + product.getQuantity());
        } else {
            System.out.println("Product Not Found");
        }

        session.close();
    }

    // UPDATE PRODUCT
    public static void updateProduct(Long id, double newPrice, int newQuantity) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            product.setPrice(newPrice);
            product.setQuantity(newQuantity);
            session.merge(product);
            System.out.println("Product Updated Successfully");
        } else {
            System.out.println("Product Not Found for Update");
        }

        tx.commit();
        session.close();
    }

    // DELETE PRODUCT
    public static void deleteProduct(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();

        Product product = session.get(Product.class, id);

        if (product != null) {
            session.remove(product);
            System.out.println("Product Deleted Successfully");
        } else {
            System.out.println("Product Not Found for Deletion");
        }

        tx.commit();
        session.close();
    }
}