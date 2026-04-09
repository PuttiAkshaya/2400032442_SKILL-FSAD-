package com.klu.Skill12_FullStackCRUD_ReactSpringBoot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.klu.Skill12_FullStackCRUD_ReactSpringBoot.model.Student;
import com.klu.Skill12_FullStackCRUD_ReactSpringBoot.service.StudentService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StudentController {

    @Autowired
    private StudentService service;

    // CREATE
    @PostMapping("/students")
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        return ResponseEntity.ok(service.saveStudent(student));
    }

    // READ
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents() {
        return ResponseEntity.ok(service.getAllStudents());
    }

    // UPDATE
    @PutMapping("/students/{id}")
    public ResponseEntity<Student> updateStudent(@PathVariable Long id,
                                                 @RequestBody Student student) {
        return ResponseEntity.ok(service.updateStudent(id, student));
    }

    // DELETE
    @DeleteMapping("/students/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {
        service.deleteStudent(id);
        return ResponseEntity.ok("Deleted Successfully");
    }
}