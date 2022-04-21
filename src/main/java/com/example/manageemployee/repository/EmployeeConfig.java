package com.example.manageemployee.repository;

import com.example.manageemployee.model.Employee;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@Configuration
public class EmployeeConfig {
  
  @Bean CommandLineRunner commandLineRunner(EmployeeRepository repository) {
    return args -> {
      JSONParser jsonParser = new JSONParser();
      try (FileReader reader = new FileReader("/Users/xuantranthithanh/Documents/JAVA/SPRING_BOOT/manageEmployee/src/main/java/com/example/manageemployee/repository/employeesBin.json")) {
        Object obj = jsonParser.parse(reader);
        JSONArray employeeList = (JSONArray) obj;
        for (Object employee : employeeList) {
          
          JSONObject jsonEmployee = (JSONObject) employee;
          
          String name = (String) jsonEmployee.get("name");
          String email = (String) jsonEmployee.get("email");
          String jobTitle = (String) jsonEmployee.get("jobTitle");
          String phone = (String) jsonEmployee.get("phone");
          String imageUrl = (String) jsonEmployee.get("imageUrl");
          String employeeCode = (String) jsonEmployee.get("employeeCode");
          
          repository.save(new Employee(name, email, jobTitle, phone, imageUrl, employeeCode));
        }
        
      } catch (FileNotFoundException e) {
        e.printStackTrace();
      } catch (IOException e) {
        e.printStackTrace();
      } catch (ParseException e) {
        e.printStackTrace();
      }
    };
  }
}
