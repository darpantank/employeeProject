package com.example.demo.dto;

import java.util.Set;

import com.example.demo.model.Employee;
import com.example.demo.security.UpdatableBCrypt;

import lombok.Data;

@Data
public class SignUpIncomingDto {
	private String username;
	private String password;
	private String employeeName;
	
	public Employee DtoToEmployee() {
		UpdatableBCrypt bCrypt=new UpdatableBCrypt(11);
		Employee emp=new Employee();
		emp.setEmployeeName(this.employeeName);
		emp.setEmail(this.username);
		emp.setPassword(bCrypt.hash(this.password));
		return emp;
	}
}
