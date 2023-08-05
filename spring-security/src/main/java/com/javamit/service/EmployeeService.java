package com.javamit.service;

import com.javamit.entity.Employee;
import com.javamit.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

	public static final String DEFAULT_ROLE = "ROLE_EMPLOYEE";
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public Employee createNewEmployee(Employee employee){
        employee.setPassword(passwordEncoder.encode(employee.getPassword()));
        employee.setRoles(DEFAULT_ROLE);
        return employeeRepository.save(employee);
    }

    public List<Employee> getEmployees(){
        return employeeRepository.findAll();
    }

    public Employee getEmployee(Integer id){
        return employeeRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Employee not found"));
    }

    public Employee changeRoleofEmpployee(Employee employee){
        Employee existingEmployee = getEmployee(employee.getId());
        existingEmployee.setRoles(employee.getRoles());
        return employeeRepository.save(existingEmployee);
    }
}
