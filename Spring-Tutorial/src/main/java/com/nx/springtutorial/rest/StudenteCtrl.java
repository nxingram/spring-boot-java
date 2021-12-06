package com.nx.springtutorial.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nx.springtutorial.entity.Studente;
import com.nx.springtutorial.service.StudenteService;

@RestController // controller di tipo REST
@RequestMapping("/api/studenti") // rotta rest api
public class StudenteCtrl {

	@Autowired // istanzia in automatico la classe
	StudenteService service;

	// metodo: get, rotta o action: /api/studenti
	@GetMapping("")
	public List<Studente> tuttiGliStudenti() {
		return this.service.findAll();
	}

	// ResponseEntity: mi permette di restituire un oggetto, e uno status code
	// metodo: get, rotta o action: /api/studenti/1
	@GetMapping("/{id}")
	public ResponseEntity<Studente> getByID(@PathVariable("id") int id) {
		// recupero lo studente tramite il service;
		Studente s = this.service.findByID(id);

		if (s != null) {
			// se lo studente esiste su database
			// posso restituire sia l'oggetto, sia codice di stato delle richiesta, in
			// questo caso 200
			return new ResponseEntity<Studente>(s, HttpStatus.OK);
		} else {
			// non ho trovato lo studente, restituisco 400
			return ResponseEntity.badRequest().build(); // builder: per restituire una response entity, chimare il
														// metodo build
		}

	}

	// metodo: get, rotta o action: /api/studenti/param?id=1
	@GetMapping("/param")
	public ResponseEntity<Studente> findByIdParam(@RequestParam("id") int id) {
		// recupero lo studente tramite il service;
		Studente s = this.service.findByID(id);

		if (s != null) {
			// se lo studente esiste su database
			// posso restituire sia l'oggetto, sia codice di stato delle richiesta, in
			// questo caso 200
			return new ResponseEntity<Studente>(s, HttpStatus.OK);
		} else {
			// non ho trovato lo studente, restituisco 400
			return ResponseEntity.badRequest().build(); // builder: per restituire una response entity, chimare il
														// metodo build
		}
	}	
	

}
