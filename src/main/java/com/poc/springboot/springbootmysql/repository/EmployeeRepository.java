package com.poc.springboot.springbootmysql.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.springboot.springbootmysql.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}
