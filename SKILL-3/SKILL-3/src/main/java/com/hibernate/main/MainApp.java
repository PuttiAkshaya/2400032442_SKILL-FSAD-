package com.hibernate.main;

import com.hibernate.entity.Product;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {

        // Create SessionFactory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Product.class)
                .buildSessionFactory();

        // Open a session
        Session session = factory.getCurrentSession();

        try {
            // Create a new product
            Product newProduct = new Product("Laptop", "Gaming Laptop", 75000, 5);

            // Start transaction
            session.beginTransaction();

            // Save the product
            session.save(newProduct);

            // Commit transaction
            session.getTransaction().commit();
            System.out.println("Product saved successfully!");

            // Fetch all products
            session = factory.getCurrentSession();
            session.beginTransaction();

            List<Product> products = session.createQuery("from Product", Product.class).getResultList();
            for (Product p : products) {
                System.out.println(p);
            }

            session.getTransaction().commit();

        } finally {
            factory.close();
        }
    }
}