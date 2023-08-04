package com.example.demo.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example.demo.dto.EmployeeSearchDto;
import com.example.demo.model.Department;
import com.example.demo.model.Designation;
import com.example.demo.model.Employee;
import com.example.demo.response.ApiResponse;
@Service
@Component
public interface EmployeeService {

	Employee getEmployeeById(long employeeId);

	ApiResponse<List<Employee>> getAllEmployee(int pageSize,int pageNumber);

	Employee saveEmployeeData(Employee employee);

	void deleteEmployeeById(long employeeId);

	Employee updateEmployeeData(long employeeId,Employee employee);

	List<Department> getEmployeesByDept();

	List<Employee> getEmployeesByDeptId(int id);

	List<Designation> getTotalRoles();

	List<Employee> getEmployeesByRoles(int id);

	ApiResponse<List<Employee>> getEmployeeByKeywordSearch(String keyword,int PageNumber);

	void deleteEmployee(Employee employee);

	void getDepartmentById(int i);

	ApiResponse<List<Employee>> filterEmployees(EmployeeSearchDto dto);

}
