package com.nx.springtutorial.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nx.springtutorial.service.StudenteService;

@Controller // controller di tipo mvc, restituisce html
@RequestMapping("/mvc") // rotta (o action) controller (servlet)
public class MvcCtrl {
	
	/*
	 * ATTENZIONE: 
	 * serve la dipendenza Thymeleaf nel pom.xml
	 * altrimenti non funziona!
	 */
	
	@GetMapping // metodo get, rotta: /mvc
	public String mvcTemplate() {
		return "mvc"; // restituisce mvc.html
	}
	
	@GetMapping("/altra-rotta") // metodo get, rotta: /mvc/altra-rotta
	public String dammiAltraRotta() {
		return "altra-pagina"; // restituisce altra-pagina.html
	}

}
