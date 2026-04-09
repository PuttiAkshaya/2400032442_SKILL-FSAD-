package com.klu.Skill12_FullStackCRUD_ReactSpringBoot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.klu.Skill12_FullStackCRUD_ReactSpringBoot.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}