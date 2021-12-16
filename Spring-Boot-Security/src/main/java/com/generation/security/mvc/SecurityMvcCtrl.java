package com.generation.security.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.security.entity.User;
import com.generation.security.form.LoginForm;
import com.generation.security.form.RegistrationForm;
import com.generation.security.service.IUserService;

@Controller
public class SecurityMvcCtrl {

	@Autowired
	private IUserService _srv;
	
	@Autowired
	private PasswordEncoder _passwordEncoder;
	
	@ModelAttribute //1
	public User loggedUser(@AuthenticationPrincipal User user) {// 2
		// 1) inietta automaticamente l'utente loggato in tutte le views di questo controller 
		// se non è logggato è == NULL
		
		// 2) @AuthenticationPrincipal == Authentication.getPrincipal()
		// assegna l'utente loggato all'argomento del metodo, in questo caso "User user"
		
		return user;
	}
	
	@GetMapping("/register")
	public String registrazioneForm(RegistrationForm form, Model model) {
		return "registrazione-form";
	}
	
	@PostMapping("/register")
	public String registrazione(@Valid RegistrationForm form, BindingResult error, Model model) {//0
		// 0) @Valid => spring-boot-starter-validation, vedi entity
		// 1) se ci sono errori, ritorno al form e li visualizzo
		// 2) controllo se lo username/email è già in uso
		// 3) se già in uso, aggiungo un errore
		// 4) dai dati del form, genero lo user, e codifico la password
		// 5) tutto ok, salvo nuovo utente
		// 6) redireziono su pagina di login
		
		//1
		if(error.hasErrors()) {
			return "registrazione-form";
		}
		
		//2
		User esiste = _srv.findByEmail(form.getUsername());
		if(esiste != null) {
			//3
			error.addError(new FieldError("username","username", "Email già in uso"));
			
			return "registrazione-form";
		}
		
		//4
		User user = form.toUser(_passwordEncoder);
		
		//5
		_srv.aggiungi(user);
		
		//6
		return "redirect:/login";
	}
	
	@GetMapping("/login")
	public String loginForm(@RequestParam(required = false) String error, Model model) {
		// 1) pagina di login custom
		// sostituisce la pagina default di spring
		// 2) visualizzo un semplice erorre generico di login sulla pagina
		
		//2
		if("true".equals(error)) {
			model.addAttribute("message", true);
		}
		
		//1
		return "login-form";
	}
	

	
}
