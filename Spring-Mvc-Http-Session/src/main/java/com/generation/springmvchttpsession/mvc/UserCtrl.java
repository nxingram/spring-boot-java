package com.generation.springmvchttpsession.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.generation.springmvchttpsession.entity.User;

// 1) Sessione Http lato server
//nome parametro del metodo login

@Controller
@SessionAttributes("user") //1
public class UserCtrl {

	// 2) N.B:  Model model: serve solo per debuggare 
	// e vedere quando lo user viene caricato o rimosso dalla sessione
	// nelle variabili di debug vedrai una chiave: user
	// e l'istanza di User con i dati
		
	@GetMapping("/loginpage")
	public String loginpage(Model model) {//2
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user, Model model) { //1 //2
		
		user.setName(user.getName().toUpperCase());
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(SessionStatus status, Model model) {//2
		
		// 3) cancello la sessione, e con esso l'utente salvato
		
		//3
		status.setComplete();
		return "redirect:/";
	}
	
}
