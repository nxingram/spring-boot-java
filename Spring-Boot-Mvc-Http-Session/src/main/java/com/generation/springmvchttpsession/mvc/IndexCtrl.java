package com.generation.springmvchttpsession.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.generation.springmvchttpsession.entity.User;

//1) Sessione Http lato server
// @SessionAttributes a volte non funziona bene
// si può usare @Autowired HttpSession session come in UserCtrl  
// e poi  session.getAttribute("user") 

@Controller
@SessionAttributes("user") //1
public class IndexCtrl {
	
	
	@RequestMapping("/")
	public String base(User user, Model model) { //user non serve se si usa session.getAttribute("user") 
		// lo user viene inserito AUTOMATICAMENTE nel model per il template
		// non è necessario aggiungerlo a mano
		//model.addAttribute(user)); non serve	
		
		return "index";
	}
}
