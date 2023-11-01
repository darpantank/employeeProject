package com.example.demo.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.demo.dao.DesignationDao;
import com.example.demo.model.Designation;
import com.example.demo.model.Employee;

public class UserDetail implements UserDetails{
	private Employee employee;
	private final String rolePrefix="ROLE_";
	
	@Autowired
	private DesignationDao designationDao;

	public UserDetail(Employee employee) {
		super();
		this.employee = employee;
	}
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> roles=new HashSet<>();
		for(Designation d:employee.getDesignations()) {
			SimpleGrantedAuthority authority=new SimpleGrantedAuthority(rolePrefix.concat(d.getRole()));
			roles.add(authority);
			
		}
		return roles;
	}

	@Override
	public String getPassword() {
		return employee.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return employee.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
