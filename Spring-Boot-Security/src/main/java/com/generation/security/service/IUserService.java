package com.generation.security.service;

import com.generation.security.entity.User;

public interface IUserService {
	User findByEmail(String username);
	User aggiungi(User user);
}
