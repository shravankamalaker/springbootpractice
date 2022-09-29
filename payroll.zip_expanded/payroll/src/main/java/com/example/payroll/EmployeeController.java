package com.example.payroll;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EmployeeController {

	private final EmployeeRepository employeeRepository;
	
	private EmployeeModelAssembler assembler;

	EmployeeController(EmployeeRepository employeeRepository, EmployeeModelAssembler employeeModelAssembler) {
		this.employeeRepository = employeeRepository;
		this.assembler = employeeModelAssembler;
	}

	@GetMapping("/employees")
	CollectionModel<EntityModel<Employee>> all() {
//		List<Employee> employees = new ArrayList<Employee>();
//		List<Employee> employeeList = employeeRepository.findAll();
		/*
		 * for( Employee employee : employeeList ) { Link link =
		 * EntityModel.of(employee, linkTo)
		 * 
		 * 
		 * }
		 */
		
		//Before Using the assembler
		/*
		 * List<EntityModel<Employee>> employees =
		 * employeeRepository.findAll().stream().map( employee ->
		 * EntityModel.of(employee,
		 * linkTo(methodOn(EmployeeController.class).one(employee.getId())).withSelfRel(
		 * ), linkTo(methodOn(EmployeeController.class).all()).withRel("employees")))
		 * .collect(Collectors.toList());
		 */
		
		//After Using the assembler
		List<EntityModel<Employee>> employees =
				 employeeRepository.findAll().stream().map( 
						 assembler::toModel)
				  .collect(Collectors.toList());
		
		return CollectionModel.of(
				employees,
				linkTo(methodOn(EmployeeController.class).all()).withSelfRel());
	}

	@PostMapping("/employees")
	ResponseEntity<?> newEmployee(@RequestBody Employee newEmployee) {
		EntityModel<Employee> entityModel =assembler.toModel(employeeRepository.save(newEmployee));
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
	}

	@GetMapping("/employees/{id}")
	EntityModel<Employee> one(@PathVariable Long id) {
		/*
		 * return employeeRepository.findById(id) .orElseThrow(() -> new
		 * EmployeeNotFoundExecption(id));
		 */

		// For HATEOAS
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(() -> new EmployeeNotFoundExecption(id));
		
		//before using assembler
		/*
		 * return EntityModel.of(employee,
		 * linkTo(methodOn(EmployeeController.class).one(id)).withSelfRel(),
		 * linkTo(methodOn(EmployeeController.class).all()).withRel("employees"));
		 */
		
		//After using assembler
		return assembler.toModel(employee);
	}

	@PutMapping("/employees/{id}")
	ResponseEntity<?> replaceEmployee(@RequestBody Employee emp, @PathVariable Long id) {
		Employee updatedEmployee = employeeRepository.findById(id).map(employee -> {
			employee.setName(emp.getName());
			employee.setRole(emp.getRole());
			return employeeRepository.save(employee);
		}).orElseGet(() -> {
			emp.setId(id);
			return employeeRepository.save(emp);
		});
		
		EntityModel<Employee> entityModel = assembler.toModel(updatedEmployee);
		return ResponseEntity.created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
		
		
	}

	@DeleteMapping("/employees/{id}")
	ResponseEntity<?> deleteEMployeee(@PathVariable Long id) {
		employeeRepository.deleteById(id);
		return ResponseEntity.noContent().build();
	}

}
