package com.generation.springmvchttpsession.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.generation.springmvchttpsession.entity.User;

@Controller
public class UserCtrl {
	// 1) Sessione Http lato server
	
	// 2) N.B:  Model model: serve solo per debuggare 
	// e vedere quando lo user viene caricato o rimosso dalla sessione
	// nelle variabili di debug vedrai una chiave: user
	// e l'istanza di User con i dati
		
	@GetMapping("/loginpage")
	public String loginpage(Model model) {//2
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user, Model model, HttpSession session) { //1 //2
		
		user.setName(user.getName().toUpperCase());
		
		// tiene traccia della sessione attraverso un cookie "jsessionid";
		// viene recuperato automaticamente con @SessionAttributes("user") negli altri controllers
		// setto lo user in sessione
		session.setAttribute("user", user); 
	
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session, Model model) {//2
		
		// cancello la sessione, e con esso l'utente salvato
		session.invalidate();
		return "redirect:/";
	}
	
}
