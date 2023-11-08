package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.global_variable.GlobalVariables;
import com.example.demo.model.Employee;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.EmployeeService;

@RestController
@ResponseBody
@RequestMapping("/api/employees")
public class ViewController implements GlobalVariables {
	@Autowired
	private EmployeeService service;
	@GetMapping("/")
	public ApiResponse<List<Employee>> listOfEmployee(@RequestParam("pageSize") Optional<Integer> pageSize,@RequestParam("pageNumber") Optional<Integer> pageNumber) {
		return service.getAllEmployee(pageSize.isPresent()?pageSize.get():DEFAULT_PAGE_SIZE,pageNumber.isPresent()?pageNumber.get():DEFAULT_PAGE_NUMBER);
	}
	@GetMapping("/{id}")
	public Employee getEmployeeById(@PathVariable("id") long employeeId) {
		Employee employee= service.getEmployeeById(employeeId);
		return employee;
	}
	@GetMapping("/find")
	public ApiResponse<List<Employee>> getEmployeeById(@RequestParam("keyword") String keyword,@RequestParam("PageNumber") Optional<Integer> PageNumber) {
		return service.getEmployeeByKeywordSearch(keyword,PageNumber.isPresent()?PageNumber.get():DEFAULT_PAGE_NUMBER);
	}
	
}
