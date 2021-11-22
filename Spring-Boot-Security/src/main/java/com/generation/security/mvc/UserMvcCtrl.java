package com.generation.security.mvc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.security.entity.User;

@Controller
@RequestMapping("/user")
public class UserMvcCtrl {

	@GetMapping
	public String paginaUser() {
		return "user";
	}
	
	
	@ModelAttribute //1
	public User loggedUser(@AuthenticationPrincipal User user) {
		// 1) inietta automaticamente l'utente loggato in tutte le views di questo controller
		// se non è logggato è == null
		
		return user;
	}
}
