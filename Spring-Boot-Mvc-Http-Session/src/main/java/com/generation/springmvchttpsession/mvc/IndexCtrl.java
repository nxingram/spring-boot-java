package com.generation.springmvchttpsession.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.generation.springmvchttpsession.entity.User;

//1) Sessione Http lato server

@Controller
@SessionAttributes("user") //1
public class IndexCtrl {
	
	// 2) N.B:  Model model: serve solo per debuggare 
	// e vedere quando lo user viene caricato o rimosso dalla sessione
	// nelle variabili di debug vedrai una chiave: user
	// e l'istanza di User con i dati
	

	
	@RequestMapping("/")
	public String base(User user, Model model) { //2
		
		// 3) se email è diversa da null, faccio toUppercase
		// 4) i valori arrivano dal /login di UserCtrl, altrimenti sono null
		
		if(user.getEmail() != null) { //3
			user.setEmail(user.getEmail().toLowerCase()); //4		
		}
		
		return "index";
	}
}
