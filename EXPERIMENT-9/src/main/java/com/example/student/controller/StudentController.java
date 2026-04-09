package com.example.student.controller;

import com.example.student.model.Student;
import com.example.student.exception.StudentNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/{id}")
    public Student getStudent(@PathVariable int id) {

        if(id <= 0) {
            throw new StudentNotFoundException("Student ID is invalid or not found");
        }

        return new Student(id, "Rahul", "Computer Science");
    }
}