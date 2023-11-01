package com.example.demo.service.impl;


import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.demo.dao.DeptDao;
import com.example.demo.dao.DesignationDao;
import com.example.demo.dao.EmployeeDao;
import com.example.demo.dao.RoleDao;
import com.example.demo.dto.EmployeeSearchDto;
import com.example.demo.dto.UpdateEmployeeRequestDto;
import com.example.demo.exception.EmployeeNotPresentException;
import com.example.demo.global_variable.GlobalVariables;
import com.example.demo.model.Department;
import com.example.demo.model.Designation;
import com.example.demo.model.Employee;
import com.example.demo.model.Laptop;
import com.example.demo.response.ApiResponse;
import com.example.demo.service.EmployeeService;
@Service
public class EmployeeServiceImpl extends EmployeeService.a.b.c.e.f implements EmployeeService,GlobalVariables {
	@PersistenceContext
    private EntityManager entityManager;
	@Autowired
	private EmployeeDao employeeDao;
	@Autowired
	private DeptDao deptDao;
	@Autowired
	private RoleDao roleDao;
	@Autowired
	private DesignationDao designationDao;
	@Override
	public Employee getEmployeeById(long employeeId) throws EmployeeNotPresentException {
		Optional<Employee> employee= this.employeeDao.findById(employeeId);
		if(employee.isPresent())
			return employee.get();
		else
			throw new EmployeeNotPresentException();
	}
	@Override
	public ApiResponse<List<Employee>>getAllEmployee(int pageSize,int pageNumber) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		Page<Employee> userPage= this.employeeDao.findAll(pageable);
		ApiResponse<List<Employee>> response = new ApiResponse<>();
        response.setData(userPage.getContent());
        response.setTotalPages(userPage.getTotalPages());
		return response;
	}
	@Override
	public Employee saveEmployeeData(Employee employee) {
		return this.employeeDao.save(employee);
	}
	@Override
	public void deleteEmployeeById(long employeeId) {
		this.employeeDao.deleteById(employeeId);
	}
	@Override
	public Employee updateEmployeeData(long employeeId,UpdateEmployeeRequestDto employee) {
		Employee employeeFromDb = employeeDao.findById(employeeId).orElseThrow(() -> new EmployeeNotPresentException());
		employeeFromDb.setEmployeeName(employee.getEmployeeName());
		employeeFromDb.setEmail(employee.getEmail());
		employeeFromDb.setPassword(employee.getPassword());
		employeeFromDb.setLaptop(new Laptop(employee.getLaptopId()));
		return employeeDao.save(employeeFromDb);
	}
	@Override
	public List<Department> getEmployeesByDept() {
		return deptDao.findAll();
	}
	@Override
	public List<Employee> getEmployeesByDeptId(int id) {
		return employeeDao.findByDepartmentId(id);		
	}
	@Override
	public List<Designation> getTotalRoles() {
		return roleDao.findAll();
	}
	@Override
	public List<Employee> getEmployeesByRoles(int id) {
		return employeeDao.findByDesignationsId(id);
	}
	@Override
	public ApiResponse<List<Employee>> getEmployeeByKeywordSearch(String keyword,int PageNumber) {
		Pageable pageable = PageRequest.of(PageNumber, 2);
		Page<Employee> userPage= this.employeeDao.findDistinctByLaptopIsNotNullAndEmployeeNameIgnoreCaseContainsOrDesignationsRoleStartingWithIgnoreCaseOrDepartmentNameStartingWithIgnoreCaseOrderByEmployeeName(keyword,keyword,keyword,pageable);
		ApiResponse<List<Employee>> response = new ApiResponse<>();
        response.setData(userPage.getContent());
        response.setTotalPages(userPage.getTotalPages());
		return response;
	}
	@Override
	public void deleteEmployee(Employee employee) {
		this.employeeDao.delete(employee);
	}
	@Override
	public void getDepartmentById(int i) {
		Department d=deptDao.findById(i).orElseThrow();
		deptDao.delete(d);
	}
	@Override
	public ApiResponse<List<Employee>> filterEmployees(EmployeeSearchDto dto) {
		// TODO Auto-generated method stub
		int pageNumber=dto.getPageNumber()!=0?dto.getPageNumber():DEFAULT_PAGE_NUMBER;
		int pageSize=dto.getPageSize()!=0?dto.getPageSize():DEFAULT_PAGE_SIZE;
		Pageable page = PageRequest.of(pageNumber, pageSize);
		
//		CriteriaBuilder cb=entityManager.getCriteriaBuilder();
//		CriteriaQuery<Employee> query = cb.createQuery(Employee.class);
//		Root<Employee> root = query.from(Employee.class);

//		List<Designation> designations=dto.getDesignations().stream().map(id->findDesignation(id)).collect(Collectors.toList());
//		query.select(root)
//        .where(root.get("designations.id").in(designations));
//		root.get("designations").in(dto.getDesignations())

//        TypedQuery<Employee> typedQuery = entityManager.createQuery(query);
//        typedQuery.setFirstResult((int) pageable.getOffset());
//        typedQuery.setMaxResults(pageable.getPageSize());
//        long totalCount=countEmployeesWithFilter(dto.getDesignations());
        Page<Employee> list=employeeDao.findDistinctByDesignationsIdInAndDepartmentIdIn(dto.getDesignations(),dto.getDepartments(),page);
        ApiResponse<List<Employee>> response=new ApiResponse<>();
        response.setTotalPages(list.getTotalPages());
        response.setData(list.getContent());
		return response;
	}
}
