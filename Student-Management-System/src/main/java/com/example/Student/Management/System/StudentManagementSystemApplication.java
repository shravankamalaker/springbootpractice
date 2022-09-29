package com.example.Student.Management.System;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.Student.Management.System.repository.StudentRepository;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;
	
	@Override
	public void run(String... args) throws Exception{
		
		/*
		 * Student s1 = new Student("Shravan","kamalaker","shravan@gmail.com");
		 * studentRepository.save(s1); Student s2 = new
		 * Student("Sachin","Tendulkar","srt@gmail.com"); studentRepository.save(s2);
		 * Student s3 = new Student("Tony","stark","tony@gmail.com");
		 * studentRepository.save(s3);
		 */
		
	}
	
	
	
}
