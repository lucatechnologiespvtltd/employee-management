package com.luca.employee.service;

import com.luca.employee.entity.Employee;
import com.luca.employee.exception.ResourceNotFoundException;
import com.luca.employee.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository repository;

    public EmployeeService(EmployeeRepository repository) {
        this.repository = repository;
    }

    // ✅ Get all employees
    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }

    // ✅ Get employee by ID
    public Employee getEmployeeById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));
    }

    // ✅ Create employee
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    // ✅ Search employees
    public List<Employee> searchEmployees(String name) {
        List<Employee> employees = repository.findByNameContainingIgnoreCase(name);

        if (employees.isEmpty()) {
            throw new ResourceNotFoundException("No employees found with name: " + name);
        }

        return employees;
    }

    // ✅ Update employee
    public Employee updateEmployee(Long id, Employee employee) {

        Employee existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        existing.setName(employee.getName());
        existing.setEmail(employee.getEmail());
        existing.setDepartment(employee.getDepartment());
        existing.setSalary(employee.getSalary());

        return repository.save(existing);
    }

    // ✅ Delete employee
    public void deleteEmployee(Long id) {

        Employee existing = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Employee not found with id: " + id));

        repository.delete(existing);
    }
}