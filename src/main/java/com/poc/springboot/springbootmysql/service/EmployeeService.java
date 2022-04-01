package com.poc.springboot.springbootmysql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.springboot.springbootmysql.entity.Employee;
import com.poc.springboot.springbootmysql.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository repo;
	
	public List<Employee> retrieveEmployee() {
		List<Employee> emp=new ArrayList<>();
		
		 repo.findAll().forEach(emp::add);
		for(Employee emp1:emp) {
			 System.out.print(emp1);
		 }
		 return emp;
				
	}

	public Employee createEmployee(Employee employee) {
		return repo.save(employee);
	}

	public Optional<Employee> retrieveEmployeeById(Integer id) {
		return repo.findById(id);
		
	}

	public void removeEmployee(Integer id) {
		repo.deleteById(id);
	}

	public void updateEmployee(Integer id, Employee employee) {
		if (repo.findById(id).isPresent()) {
			repo.save(employee);
		}
	}
	
}
