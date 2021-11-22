package com.generation.security.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.security.entity.User;

@Repository
public interface IUserRepo extends JpaRepository<User, Long> {

	User findByUsername(String username);
}
