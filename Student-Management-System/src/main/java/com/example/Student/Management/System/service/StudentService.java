package com.example.Student.Management.System.service;

import java.util.List;

import com.example.Student.Management.System.entity.Student;

public interface StudentService {

	List<Student> getAllStudents();

	Student saveStudent(Student student);
	
	Student getStudentById(long id);
	
	Student updateStudent(Student student);
	
	void deleteStudent(long id);
	
}
