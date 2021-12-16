package com.generation.security.mvc;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.generation.security.entity.User;

@Controller
public class IndexMvcCtrl {

	@ModelAttribute // 1
	public User loggedUser(@AuthenticationPrincipal User user) { // 2
		// 1) inietta automaticamente l'utente loggato in tutte le views di questo controller 
		// se non è logggato è == NULL
		
		// 2) @AuthenticationPrincipal == Authentication.getPrincipal()
		// assegna l'utente loggato all'argomento del metodo, in questo caso "User user"
		
		return user;
	}

	@GetMapping("/")
	public String index(Model model) { // 2
		// 1) se c'è un utente loggato lo visualizzo sulla pagina
		// inietto automaticamente l'utente in tutte le views di questo controller
		// 2) Model model => non necessario, usato solo per visualizzare la variabile in
		// debug

		return "index";
	}

}
