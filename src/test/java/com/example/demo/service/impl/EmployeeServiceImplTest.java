package com.example.demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.dao.EmployeeDao;
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
	@Autowired
 	private EmployeeServiceImpl empService=new EmployeeServiceImpl();
	@Autowired
	private  EmployeeDao dao;
//	@BeforeEach
//	void setUp() {
//		empService=new EmployeeServiceImpl(dao);
////		createdEmp.setEmployeeName("demo");
////		createdEmp=dao.save(createdEmp);
//		System.out.println(dao.findAll());
////		assertThat(createdEmp.getEmployeeId()!=0).isTrue();
//	}
	@Test
	void testGetEmployeeById() {
//		boolean actual=empService.getEmployeeById(5L)!=null?true:false;
//		assertThat(actual).isTrue();
		
	}
//	@Test
//	void getAllPerson() {
//		empService.getAllEmployee(10, 0);
//		verify(dao).findAll();
//	}
	
//	@Test
//	void testDeleteEmployeeData(Employee employee) {
//		
//	}
	

}
