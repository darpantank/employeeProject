package com.example.demo.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.EmployeeDao;
import com.example.demo.service.EmployeeService;
@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
 	private EmployeeServiceImpl empService=mock(EmployeeServiceImpl.class);
	@Mock
	private EmployeeService service;
	@Mock
	private EmployeeDao dao;
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
		boolean actual=empService.getEmployeeById(5L)!=null?true:false;
		assertThat(actual).isTrue();
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
