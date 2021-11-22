package com.generation.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.generation.security.entity.User;
import com.generation.security.repo.IUserRepo;

/**
 * Implementa intefaccia UserDetailsService : configurazione necessaria per spring security <br>
 * 
 * Esempio => https://github.com/habuma/spring-in-action-5-samples/tree/master/ch04/tacos/src/main/java/tacos/security
 */

@Service
public class UserService implements UserDetailsService, IUserService {

	@Autowired
	private IUserRepo _repo;

	/**
	 * N:B: essenziale per spring Security! <br>
	 * override obbligatorio del metodo di UserDetailsService
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// 1) cerca lo username sul database
		// 3) lancia eccezione se l'utente non viene trovato
		// 3) return un utente che implementa UserDeytails
		
		//1
		User user = _repo.findByUsername(username);
		
		//2
		if(user == null) {
			throw new UsernameNotFoundException("User " + username + " not found!");
		}
		
		//3
		return user;
	}

	@Override
	public User findByEmail(String username) {
		return _repo.findByUsername(username);
	}

	@Override
	public User aggiungi(User user) {
		return _repo.save(user);
	}
}
