package com.generation.springmvchttpsession.mvc;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.generation.springmvchttpsession.entity.User;

@Controller
public class UserCtrl {
	
	// Sessione Http lato server
	// può anche venire iniettata come parametro di un singolo metodo
	@Autowired
	HttpSession session;
		
	@GetMapping("/loginpage")
	public String loginpage() {
		return "login";
	}
	
	@PostMapping("/login")
	public String login(User user) { // dati provenienti dal form vengono settati nello user

		// Tiene traccia della sessione attraverso un cookie "JSESSIONID";
		// viene recuperato automaticamente con @SessionAttributes("user") negli altri controllers
		
		// setto lo user in sessione
		session.setAttribute("user", user); 
	
		return "redirect:/";
	}
	
	@GetMapping("/logout")
	public String logout() {
		
		// cancello la sessione, e con esso l'utente salvato
		session.invalidate();
		return "redirect:/";
	}
	
}
