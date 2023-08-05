package com.javamit.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.javamit.entity.Employee;
import com.javamit.repository.EmployeeRepository;


@Service
public class EmployeeUserDetailsService implements UserDetailsService {
	
    @Autowired
    private EmployeeRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Employee> employee = repository.findByUsername(username);

        return employee
                .map(EmployeeUserDetails::new)
                .orElseThrow(()-> new UsernameNotFoundException("user is not found"));
    }
}
