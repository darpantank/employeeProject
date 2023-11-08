package com.example.demo.dao;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Laptop;

public interface LaptopDao extends CrudRepository<Laptop, Long> {

}
