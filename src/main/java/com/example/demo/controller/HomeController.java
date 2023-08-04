package com.example.demo.controller;
 	
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Department;
import com.example.demo.model.Designation;
import com.example.demo.model.Employee;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.EmployeeService;
import com.example.demo.dto.EmployeeSearchDto;
import com.example.demo.global_variable.GlobalVariables;

@RestController
@ResponseBody
@RequestMapping("/api/employees")
public class HomeController implements GlobalVariables{
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
	@PostMapping("/")	
	public ResponseEntity<Employee> saveEmployeeData(@RequestBody Employee employee) {
		return new ResponseEntity<Employee>(service.saveEmployeeData(employee),HttpStatus.CREATED) ;
	}
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable("id") long employeeId) {
		service.deleteEmployeeById(employeeId);
	} 
	@PutMapping("/{id}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable("id") long employeeId,@RequestBody Employee employee) {
		System.out.println(employee);
		return new ResponseEntity<Employee>(service.updateEmployeeData(employeeId,employee),HttpStatus.OK);
	}
	@PostMapping("/upload-img")
	public void uploadPhotos(){
//		System.out.println("Hello "+file.getOriginalFilename());
		service.getDepartmentById(3);
		
	}
	@GetMapping("/dept")
	public List<Department> findDepartments(){
		return service.getEmployeesByDept();
	}
	@GetMapping("/dept/{id}")
	public List<Employee> findEmployeeWithDepartmentId(@PathVariable("id") int id){
		return service.getEmployeesByDeptId(id);
	}
	@GetMapping("/roles")
	public List<Designation> findRoles(){
		return service.getTotalRoles();
	}
	@GetMapping("/roles/{id}")
	public List<Employee> findEmployeeWithRolesId(@PathVariable("id") int id){
		return service.getEmployeesByRoles(id);
	}
	@GetMapping("/filter/")
	public ApiResponse<List<Employee>> findEmployeesWithFilter(@RequestBody EmployeeSearchDto dto){
		System.out.println(dto);
		return service.filterEmployees(dto);
	}
}
