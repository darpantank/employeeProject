package com.example.demo.dao;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
@Repository
public class EmployeeRepo {
	@Autowired
    private EntityManagerFactory factory;
}
