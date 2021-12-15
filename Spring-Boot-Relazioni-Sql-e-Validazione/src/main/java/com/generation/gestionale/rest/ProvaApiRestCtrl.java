package com.generation.gestionale.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.gestionale.entity.Articolo;
import com.generation.gestionale.entity.Cliente;
import com.generation.gestionale.entity.Impiegato;
import com.generation.gestionale.entity.Ordine;
import com.generation.gestionale.entity.OrdineDettaglio;
import com.generation.gestionale.entity.Ufficio;
import com.generation.gestionale.service.iservice.IArticoloService;
import com.generation.gestionale.service.iservice.IClienteService;
import com.generation.gestionale.service.iservice.IImpiegatoService;
import com.generation.gestionale.service.iservice.IOrdineDettaglioService;
import com.generation.gestionale.service.iservice.IOrdineService;
import com.generation.gestionale.service.iservice.IUfficioService;

/**
 * Controller per verificare funzionamento delle relazioni<br>
 */
@RestController
@RequestMapping("/api/v1")
public class ProvaApiRestCtrl {

	@Autowired
	private IUfficioService _sUfficio;

	@Autowired
	private IImpiegatoService _sImpiegato;

	@Autowired
	private IOrdineService _sOrdine;
	
	@Autowired
	private IClienteService _sCliente;

	@Autowired
	private IArticoloService _sArticolo;

	@Autowired
	private IOrdineDettaglioService _sOrdineDettaglio;

	@GetMapping("/ufficio")
	public List<Ufficio> allUffici() {
		return _sUfficio.findAll();
	}

	@GetMapping("/impiegato")
	public List<Impiegato> allImpiegati() {
		return _sImpiegato.findAll();
	}

	@GetMapping("/ordine")
	public List<Ordine> allOrdini() {
		return _sOrdine.findAll();
	}
	
	@GetMapping("/cliente")
	public List<Cliente> allClienti() {
		return _sCliente.findAll();
	}

	@GetMapping("/articolo")
	public List<Articolo> allarticoli() {
		return _sArticolo.findAll();
	}

	@GetMapping("/dettaglio")
	public List<OrdineDettaglio> allOrdiniDettaglio() {
		return _sOrdineDettaglio.findAll();
	}

}
