package com.example.employee;

import java.util.ArrayList;
// import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// import lombok.RequiredArgsConstructor;

@Service
// @RequiredArgsConstructor
public class EmployeeServiceImp implements EmployeeService {

    // private final EmployeeRepository employeeRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    List<Employee> employees = new ArrayList<>();

    @Override
    public String createEmployee(Employee employee) {
        EmployeeEntity employeeEntity = new EmployeeEntity();
        BeanUtils.copyProperties(employee, employeeEntity);
        employeeRepository.save(employeeEntity);
        // employees.add(employee);
        return "saved";

    }

    @Override
    public Employee readEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).get();

        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeEntity,employee);

        return employee;
    }

    @Override
    public List<Employee> readEmployees() {
        List<EmployeeEntity> employeesList = employeeRepository.findAll();
        List<Employee> employees = new ArrayList<>();

        for(EmployeeEntity employeeEntity : employeesList) {
            Employee emp = new Employee();
            // emp.setName(employeeEntity.getName());
            // emp.setEmail(employeeEntity.getEmail());
            BeanUtils.copyProperties(employeeEntity, emp);  
            employees.add(emp);
        }
        return employees;

    }

    @Override
    public boolean deleteEmployee(long id) {

        // employees.remove(id);
        // return true;

        // Iterator<Employee> iterator = employees.iterator();

        // while (iterator.hasNext()) {
        //     Employee employee = iterator.next();
        //     if (employee.getId() == id) {
        //         iterator.remove();
        //         return true;
        //     }
        // }
        // return false;

        EmployeeEntity emp = employeeRepository.findById(id).get();
        employeeRepository.delete(emp);
        return true;
    }

    @Override
    public String updateEmployee(Long id, Employee employee) {

        EmployeeEntity existingEmployee = employeeRepository.findById(id).get();

        existingEmployee.setEmail(employee.getEmail());
        existingEmployee.setName(employee.getName());
        existingEmployee.setPhone(employee.getPhone());

        employeeRepository.save(existingEmployee);
        return "updated";
    }

    

}
