package com.example.manageemployee.service;

import com.example.manageemployee.exception.UserNotFoundException;
import com.example.manageemployee.model.Employee;
import com.example.manageemployee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionException;
import org.springframework.stereotype.Service;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeService {
  private final EmployeeRepository employeeRepository;
  
  @Autowired
  public EmployeeService(EmployeeRepository employeeRepository) {
    this.employeeRepository = employeeRepository;
  }
  
  public Employee addEmployee(Employee employee) {
    employee.setEmployeeCode(UUID.randomUUID().toString());
    return employeeRepository.save(employee);
  }
  
  public List<Employee> findAllEmployees() {
    return employeeRepository.findAll();
  }
  
  public Employee updateEmployee(Employee employee) {
    return employeeRepository.save(employee);
  }
  
  public Employee findEmployeeById(Long id) {
    return employeeRepository.findEmployeeById(id)
                             .orElseThrow(() -> new UserNotFoundException("User by id " + id + " was not " + "found"));
  }
  
  public void deleteEmployee(Long id) {
    employeeRepository.deleteById(id);
  }
}
