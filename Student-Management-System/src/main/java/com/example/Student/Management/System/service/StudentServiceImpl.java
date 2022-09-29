package com.example.Student.Management.System.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Student.Management.System.entity.Student;
import com.example.Student.Management.System.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService {

	StudentRepository studentRepository;  
	
	public StudentServiceImpl(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}

	@Override
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	@Override
	public Student saveStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public Student getStudentById(long id) {
		return studentRepository.findById(id).get();
	}

	@Override
	public Student updateStudent(Student student) {
		return studentRepository.save(student);
	}

	@Override
	public void deleteStudent(long id) {
		studentRepository.findById(id);
		studentRepository.deleteById(id);
	}

}
