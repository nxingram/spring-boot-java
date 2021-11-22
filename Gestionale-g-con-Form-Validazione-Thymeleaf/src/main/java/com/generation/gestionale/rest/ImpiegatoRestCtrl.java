package com.generation.gestionale.rest;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.gestionale.entity.Impiegato;
import com.generation.gestionale.entity.Ufficio;
import com.generation.gestionale.service.iservice.IImpiegatoService;
import com.generation.gestionale.service.iservice.IUfficioService;

@RestController
@RequestMapping("/api/impiegato")
public class ImpiegatoRestCtrl {

	@Autowired
	private IImpiegatoService _sImpiegato;
	
	@Autowired
	private IUfficioService _sUfficio;
	
	@GetMapping("/prova-new")
	public Impiegato provaAddImpiegato() {
		
		// creo 1 impiegato di prova
		Impiegato i = new Impiegato();
		i.setNome("nome-test");
		i.setCognome("cognometest-test");
		i.setRuolo("ruolo-test");
		i.setStipendio(new BigDecimal(1100.33));
				
		// aggiungo 1 ufficio come oggetto, non come ufficio_id
		Ufficio u = _sUfficio.findAll().stream().findFirst().get();
		i.setUfficio(u);	
		
		// aggiungo un capo di questo impiegato, sempre come oggetto, non come id impiegato
		Impiegato capo = _sImpiegato.findAll().stream().findFirst().get();
		i.setRifTo(capo);
		
		
		// salvo nuovo utente e lo restituisco via json
		return _sImpiegato.save(i);
	}
	
	@GetMapping("")
	public List<Impiegato> allImpiegati() {
		return _sImpiegato.findAll();
	}
}
