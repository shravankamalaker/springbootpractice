package com.example.payroll;

public class EmployeeNotFoundExecption extends RuntimeException{
 

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundExecption(Long id) {
		super("Could not find employee with id = "+id);
	}
	
	
	
}
