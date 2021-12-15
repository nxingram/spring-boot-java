package com.nx.springtutorial.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nx.springtutorial.entity.Studente;
import com.nx.springtutorial.service.StudenteService;

@Controller // controller di tipo mvc, restituisce html
@RequestMapping("/mvc") // rotta (o action) controller (servlet)
public class MvcCtrl {
	
	/*
	 * ATTENZIONE: 
	 * serve la dipendenza Thymeleaf nel pom.xml
	 * altrimenti non funziona!
	 */
	
	@Autowired
	StudenteService srv;
	
	// Tutti gli studenti
	@GetMapping // metodo get, rotta: /mvc
	public String findAll(Model model) {
		// model: contenitore che viene utilizzato per passare i dati alla vista thymeleaf
		// passo l'elenco degli studenti
		// allStudenti: è la chiave per recuperare i dati nella vista
		model.addAttribute("allStudenti", srv.findAll());
		
		// restituisco la vista thymeleaf mvc.html
		return "mvc"; 
	}
	
	
	// Aggiorna studente form
	@GetMapping("/{id}") // metodo get, rotta: /mvc/{id}
	public String updForm(@PathVariable("id") int id, Model model) { // ("id") in pathVariable si può omettere se ha lo stesso nome presente nella rotta
		// cerco lo studente da modificare
		Studente studente = srv.findByID(id);
		
		// se l'id non è presente su db, redireziono sulla lista studenti
		if(studente==null)
			return "redirect:/mvc";
		
		// lo aggiungo al model
		model.addAttribute("studente", studente); 
		
		// inoltro i dati alla pagina thymeleaf studente-form.html
		return "studente-form";	
	}
	
	// Salva: sia nuovo studente sia aggiorna studente
	@PostMapping("/save") // method:post, rotta: /mvc/save
	public String saveStudente(Studente s) {
		// salva studente 
		srv.saveOne(s);
		
		// redireziono su altro metodo del controller
		// in questo caso rimando alla lista degli studenti
		return "redirect:/mvc"; 
	}
	
	// New studente form: riuso stesso di update form
	@GetMapping("/addform") // metodo get, rotta: /mvc/addform
	public String addForm(Model model) {
		
		// aggiungo studente vuoto
		// viene bindato(legato) ai campi del form
		model.addAttribute("studente", new Studente());
		
		return "studente-form";
	}
	
	// Delete
	@GetMapping("/delete/{id}") // metodo get, rotta: /mvc/delete/{id}
	public String delStudente(@PathVariable("id") int id){// ("id") in pathVariable si può omettere se ha lo stesso nome presente nella rotta
		
		// cerco lo studente da cancellare
		Studente studente = srv.findByID(id);
		
		// se l'id non è presente su db, redireziono sulla lista studenti
		if(studente==null)
			return "redirect:/mvc";
		
		// cancello studente
		srv.delOne(id);
		
		return "redirect:/mvc";
	}
}
