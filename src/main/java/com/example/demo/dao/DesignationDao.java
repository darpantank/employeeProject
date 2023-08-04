package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Designation;

public interface DesignationDao extends JpaRepository<Designation,Integer> {

}
