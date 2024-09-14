package com.example.employee;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@CrossOrigin("http://localhost:3000/")
public class EmpController {

    // EmployeeService employeeService = new EmployeeServiceImp();

    @Autowired
    EmployeeService employeeService;

    @GetMapping("employees")
    public List<Employee> getEmployee() {

        return employeeService.readEmployees();
    }

    @GetMapping("employees/{id}")
    public Employee getEmployeeBYId(@PathVariable Long id) {

        return employeeService.readEmployee(id);
    }

    @PostMapping("employees")
    public String postEmployee(@RequestBody Employee employee) {

        return employeeService.createEmployee(employee);
        // employees.add(employee);

    }

    @DeleteMapping("employees/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        if (employeeService.deleteEmployee(id))
        return "deleted";
             
        else
        return "nondeleted";
        

    }

    @PutMapping("employees/{id}")
    public String putMethodName(@PathVariable Long id, @RequestBody Employee employee) {
        //TODO: process PUT request
        
        return employeeService.updateEmployee(id, employee);
    }

}
