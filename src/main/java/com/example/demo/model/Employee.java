package com.example.demo.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "employee_id")
	private long employeeId;
	
	@Column(name = "employee_name")
	private String employeeName;
	
	@Column(name = "email")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "secret_key")
	private String secretKey;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@Column(name= "designation")
	private List<Designation> designations;
	
	
	@OneToOne(cascade = CascadeType.ALL)
	private Laptop laptop;
	
	
	@ManyToOne(cascade = CascadeType.DETACH)
	private Department department;
}
