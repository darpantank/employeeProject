package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Department;

public interface DeptDao extends JpaRepository<Department, Integer>{

}
