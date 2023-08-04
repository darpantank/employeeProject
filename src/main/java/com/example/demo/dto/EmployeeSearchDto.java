package com.example.demo.dto;

import java.util.List;

import lombok.Data;
@Data
public class EmployeeSearchDto {
	private List<Integer> designations;
	private List<Integer> departments; 
	private int pageNumber;
	private int pageSize;
}
