package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.exception.EmployeeNotPresentException;
import com.example.demo.model.Employee;
import com.example.demo.security.MyUserDetails;
//implements UserDetailsService
@Service
public class MyEmployeeDetailsService  {
	@Autowired
	EmployeeDao employeeDao;
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		 Optional<Employee> emp= employeeDao.findByEmployeeName(username);
//		 emp.orElseThrow(()->new EmployeeNotPresentException());
//		 return emp.map(MyUserDetails::new).get();
//	}

}
