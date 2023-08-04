package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "laptop")
@Data
public class Laptop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "laptop_id")
	private int laptopId;
	@Column(name = "laptop_model")
	private String laptopModel;
	@Column(name = "laptop_brand")
	private String laptopBrand;
	@JsonIgnore
	@OneToOne(mappedBy = "laptop")
	private Employee employee;
}
