package com.playload;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

public class User {
	private Integer id;
	private String name;
	private String department;
	
	public User() {}
	
	
	public User(Integer id,String name, String department) {
		super();
		this.name = name;
		this.department = department;
	}
	
	
	
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", department=" + department + "]";
	}
	
	
	
	
}
