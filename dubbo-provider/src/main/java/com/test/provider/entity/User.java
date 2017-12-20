package com.test.provider.entity;

import java.io.Serializable;

public class User implements Serializable {
	private Integer user_id;
	private String user_name;
	private String password;
	public Integer getId() {
		return user_id;
	}
	public void setId(Integer id) {
		this.user_id = id;
	}
	public String getName() {
		return user_name;
	}
	public void setName(String name) {
		this.user_name = name;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
