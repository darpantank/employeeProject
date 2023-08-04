package com.example.demo.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Employee;
public interface EmployeeDao extends PagingAndSortingRepository<Employee, Long> {
	public List<Employee> findByEmployeeNameOrEmployeeId(String name,long id);
	public List<Employee> findByDepartmentId(int id);
	public List<Employee> findByDesignationsId(int id);
	public Page<Employee> findDistinctByLaptopIsNotNullAndEmployeeNameIgnoreCaseContainsOrDesignationsRoleStartingWithIgnoreCaseOrDepartmentNameStartingWithIgnoreCaseOrderByEmployeeName(String keyword,String keyword1,String keyword2, Pageable pageable );
	public Page<Employee> findDistinctByDesignationsIdInAndDepartmentIdIn(List<Integer> designations,List<Integer> department,Pageable page);
}
