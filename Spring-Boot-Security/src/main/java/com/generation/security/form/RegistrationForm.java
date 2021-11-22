package com.generation.security.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.generation.security.entity.User;

public class RegistrationForm {
	// 1) spring-boot-starter-validation
	
	@Email //1
	@NotBlank //1
	@Length(min = 4, max=30) //1
	private String username;
	
	@NotBlank
	@Length(min = 4, max=30)
	private String password;

	@NotBlank
	@Length(min = 4, max = 30)
	private String name;
	
	
	public User toUser(PasswordEncoder passwordEncoder) {
		// 2) creo un nuovo user e imposto i valori del form
		// 3) codifico la password con il password encoder impostato in secrity config
		
		User user =  new User(); //2
		user .setUsername(username);
		user.setPassword(passwordEncoder.encode(password)); //3
		user.setName(name);
		
		return user;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
