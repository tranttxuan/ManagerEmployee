package com.example.manageemployee.controller;

import com.example.manageemployee.model.Employee;
import com.example.manageemployee.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
  private final EmployeeService employeeService;
  
  public EmployeeController(EmployeeService employeeService) {
    this.employeeService = employeeService;
  }
  
  @GetMapping("/all")
  public ResponseEntity<List<Employee>> getAllEmployee(){
    List<Employee> employees =  employeeService.findAllEmployees();
    return new ResponseEntity<>(employees, HttpStatus.OK);
  }
  
  @GetMapping("/find/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") Long id){
    Employee employee = employeeService.findEmployeeById(id);
    return new ResponseEntity<>(employee, HttpStatus.OK);
  }
  
  @PostMapping("/add")
  public ResponseEntity<Employee> addNewEmployee(@RequestBody Employee employee){
    Employee newEmployee =  employeeService.addEmployee(employee);
    return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
  }
  
  @PutMapping("/update")
  public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
    Employee updatedEmployee = employeeService.updateEmployee(employee);
    return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
  }
  
  @DeleteMapping("/delete/{id}")
  public ResponseEntity<?> deleteEmployee(@PathVariable("id") Long id){
    employeeService.deleteEmployee(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
