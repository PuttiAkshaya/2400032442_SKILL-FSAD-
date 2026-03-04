package com.klu.library.controller;

import com.klu.library.model.Book;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class LibraryController {

    List<Book> books = new ArrayList<>();

    @GetMapping("/welcome")
    public String welcome() {
        return "Welcome to Online Library System";
    }
   
    @GetMapping("/count")
    public int countBooks() {
        return 100;
    }

    @GetMapping("/price")
    public double getPrice() {
        return 499.99;
    }

    @GetMapping("/books")
    public List<String> getBooks() {
        return Arrays.asList(
                "Java Programming",
                "Spring Boot Guide",
                "Microservices Architecture",
                "Data Structures"
        );
    }

    @GetMapping("/books/{id}")
    public String getBookById(@PathVariable int id) {
        return "Details for Book ID: " + id;
    }

    @GetMapping("/search")
    public String searchBook(@RequestParam String title) {
        return "Searching for book: " + title;
    }

    @GetMapping("/author/{name}")
    public String getAuthor(@PathVariable String name) {
        return "Books written by " + name;
    }

    // 8 Add book using POST
    @PostMapping("/addbook")
    public String addBook(@RequestBody Book book) {
        books.add(book);
        return "Book added successfully";
    }

    // 9 View all books
    @GetMapping("/viewbooks")
    public List<Book> viewBooks() {
        return books;
    }
    @GetMapping("/")
    public String home() {
        return "Library API is running";
    }
}