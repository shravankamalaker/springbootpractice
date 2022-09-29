package com.example.Student.Management.System.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Student.Management.System.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
