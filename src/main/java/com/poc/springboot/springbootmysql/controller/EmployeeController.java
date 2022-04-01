package com.poc.springboot.springbootmysql.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.poc.springboot.springbootmysql.entity.Employee;
import com.poc.springboot.springbootmysql.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@PostMapping("/v1/create")
	@ResponseStatus(code = HttpStatus.CREATED)
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
	return ResponseEntity.ok(service.createEmployee(employee));
	}
	
	@GetMapping("/v1/employee")
	public ResponseEntity<List<Employee>> retrieveEmployee() {
		return ResponseEntity.ok(service.retrieveEmployee());
	}
	
	@GetMapping("v1/employee/{id}")
	public Optional<Employee> retrieveEmployeeById(@PathVariable Integer id) {
		Optional<Employee> emp = service.retrieveEmployeeById(id);
		if(!emp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not present with id " +id);
		}
		else {
			return emp;
		}
	}
	
	@PutMapping("/v1/udpate/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Integer id,@RequestBody Employee employee ){
		Optional<Employee> emp = service.retrieveEmployeeById(id);
		if(!emp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not present with id " +id);
		}
		else {
			service.updateEmployee(id,employee);
			return new ResponseEntity<Employee>(HttpStatus.ACCEPTED);
		}
	}
	
	@DeleteMapping("v1/employee/{id}")
	public ResponseEntity<Employee> removeEmployee(@PathVariable Integer id){
		Optional<Employee> emp = service.retrieveEmployeeById(id);
		if(!emp.isPresent()) {
			throw new EmployeeNotFoundException("Employee not present with id " +id);
		}
		else {
			service.removeEmployee(id);
			return new ResponseEntity<Employee>(HttpStatus.ACCEPTED);
		}
	}
}
