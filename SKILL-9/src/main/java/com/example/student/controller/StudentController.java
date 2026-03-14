package com.example.student.controller;

import com.example.student.exceptions.InvalidInputException;
import com.example.student.exceptions.StudentNotFoundException;
import com.example.student.model.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private Map<Long, Student> students = new HashMap<>();
    
    public StudentController() {
        students.put(1L, new Student(1L, "John Doe", "john@example.com"));
        students.put(2L, new Student(2L, "Jane Smith", "jane@example.com"));
        students.put(100L, new Student(100L, "Bob Wilson", "bob@example.com"));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new InvalidInputException("Student ID must be positive number");
        }
        
        Student student = students.get(id);
        if (student == null) {
            throw new StudentNotFoundException("Student with ID " + id + " not found");
        }
        return ResponseEntity.ok(student);
    }
    
    @PostMapping
    public ResponseEntity<String> createStudent(@RequestBody Student student) {
        if (student.getId() == null || student.getName() == null || student.getName().trim().isEmpty()) {
            throw new InvalidInputException("ID and name required");
        }
        students.put(student.getId(), student);
        return ResponseEntity.ok("Created: " + student.getName());
    }
}
