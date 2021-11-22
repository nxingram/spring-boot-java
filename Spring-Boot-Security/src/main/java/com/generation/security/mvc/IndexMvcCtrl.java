package com.generation.security.mvc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.generation.security.entity.User;

@Controller
public class IndexMvcCtrl {

	@GetMapping("/")
	public String index(Model model) { //2
		// 1) se c'è un utente loggato lo visualizzo sulla pagina
		// inietto automaticamente l'utente in tutte le views di questo controller
		// 2) Model model => non necessario, usato solo per visualizzare la variabile in debug
			
		return "index";
	}
	
	@ModelAttribute //1
	public User loggedUser(@AuthenticationPrincipal User user) {
		return user;
	}
}
