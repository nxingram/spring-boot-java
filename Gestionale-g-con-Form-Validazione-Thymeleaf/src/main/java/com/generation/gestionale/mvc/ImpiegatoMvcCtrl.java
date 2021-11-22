package com.generation.gestionale.mvc;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.generation.gestionale.entity.Impiegato;
import com.generation.gestionale.service.iservice.IImpiegatoService;
import com.generation.gestionale.service.iservice.IUfficioService;

/**
 * Controller MVC: restituisce pagine Html <br>
 * Thymeleaf : https://www.baeldung.com/thymeleaf-in-spring-mvc <br>
 * ModelAttribute: https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation
 */
@Controller
@RequestMapping("/mvc/impiegato")
public class ImpiegatoMvcCtrl {

	@Autowired
	private IImpiegatoService _sImpiegato;
	
	@Autowired
	private IUfficioService _sUfficio;


	@GetMapping
	public String allImpiegati(Model model) { //1
		// 1) Model model => non necessario, usato solo per visualizzare in debug il contenuto di model
		// lista impiegati già presente nel model,
		// caricata dal metodo addModelAttributes() per tutti i metodi del controller
		
		return "/impiegato/impiegato-list";
	}


	@PostMapping
	public String addImpiegato(@Valid Impiegato imp, BindingResult errors, Model model) {
		// 1) controllo se ci sono errori di validazione
		// il controllo avviene treamite l'annotazione @Valid
		// e le annotazioni sulle proprietà nell'entity Impiegato
		// 2) se ho errori riapro il form e visualizzo gli errori
		// 3) a Non ho errori, salvo l'impiegato
		// 4) redireziono a lista impiegati
		
		if (errors.hasErrors()) { //1
			// 2
			return "/impiegato/impiegato-form";
		}

		//3
		_sImpiegato.save(imp);

		//4
		return "redirect:/mvc/impiegato";
	}
	

	@GetMapping(value = "/aggiungi")	
	public String addImpiegatoForm(Impiegato imp, Model model) {//1 //2
		// 1) Impiegato imp => usato da thymeleaf, aggiunto automaticamente al model con nomeVariabile = "impiegato"
		// 2) Model model => non necessario, usato solo per visualizzare in debug il contenuto di  model 
		
		return "/impiegato/impiegato-form";
	}
	
	@GetMapping(value = "/modifica/{id}")	
	public String addImpiegatoForm(@PathVariable Integer id, Model model) {//1 //2
		// 1) Id impiegato da modificare
		// 2) Model model => dati da visualizzare nella pagina thymeleaf
		// 3) recupero l'impiegato tramite id e lo aggiungo al model 
		
		//3
		model.addAttribute("impiegato", _sImpiegato.findByID(id));
		return "/impiegato/impiegato-form";
	}
	
	/**
	 * PathVariable : leggo id da percorso url
	 * @param id : dell'impiegato da cancellare
	 * @return pagina di messaggio: errore o cancellato correttamente
	 */
	@GetMapping("/delete/{id}")
	public String delImpiegato(@PathVariable Integer id, Model model) {//1 //2
		// 1) id impiegato da cancellare preso dal path url
		// 2) Model model => non necessario, usato solo per visualizzare in debug il contenuto di  model 
		// 3) cerco se impiegato è presente su db
		// 4) se impiegato id non esiste
		// 5) restituisco messaggio di errore
		// 6) se c'è su database, allora lo cancello
		// 7) restituisco messaggio di successo
		// 8) errore cancellazione utente, causato da foreign key constraint
		// 9) pagina comune per visualizzazione di messaggi
		
		//3
		var imp = _sImpiegato.findByID(id);
		
		if(imp == null) { //4			
			//5
			model.addAttribute("message", "Impiegato non presentre su database");
			
		}else {
			try {
				//6
				_sImpiegato.delImpiegato(imp);
				
				//7
				model.addAttribute("message", "Impiegato cancellato correttamente");
				
			} catch (DataIntegrityViolationException e) {
				//8
				model.addAttribute("message", "Non è possibile cancellare l'impiegato, controllare referenti collegati");
				
				e.printStackTrace();
			}	
			
			
			
		}
		
		//9
		return "/common/message";
	}
	
	/**
	 * \@ModelAttribute => paragrafo 2.1 => https://www.baeldung.com/spring-mvc-and-the-modelattribute-annotation <br>
	 */
	@ModelAttribute //1
	public void addModelAttributes(Model model) {
		// 1) carica automaticamente nel model gli attributi (2) per tutti i metodi di questo controller
		
		//2
		model.addAttribute("impiegati", _sImpiegato.findAll());
		model.addAttribute("uffici", _sUfficio.findAll());
	}
	

	


}
