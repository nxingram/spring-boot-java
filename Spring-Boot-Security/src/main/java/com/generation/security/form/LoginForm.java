package com.generation.security.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class LoginForm {
	// 1) spring-boot-starter-validation
	
	@Email //1
	@NotBlank //1
	@Length(min = 4, max=30) //1
	private String username;
	
	@NotBlank
	@Length(min = 4, max=30)
	private String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
	 
}
