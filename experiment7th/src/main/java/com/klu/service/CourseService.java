package com.klu.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klu.model.Course;
import com.klu.repository.CourseRepository;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    // Add Course
    public Course addCourse(Course course) {
        return courseRepository.save(course);
    }

    // Get All Courses
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    // Get Course By Id
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    // Update Course
    public Course updateCourse(Long id, Course course) {
        course.setCourseId(id);
        return courseRepository.save(course);
    }

    // Delete Course
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }

    // Search Course by Title
    public List<Course> searchCourse(String title) {
        return courseRepository.findByTitleContainingIgnoreCase(title);
    }
}