package com.generation.fileupload.restcrtl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.generation.fileupload.entity.Veicolo;
import com.generation.fileupload.service.IVeicoloService;

/**
 * https://www.bezkoder.com/spring-boot-upload-file-database/ REST Usare Postman
 * o simile per effettuare le requests di prova
 * 
 * Multipart Content-Type:
 * https://www.w3.org/Protocols/rfc1341/7_2_Multipart.html
 */
@RestController
@RequestMapping("/api/veicolo")
@CrossOrigin("http://localhost:8081") // necessario se la request proviene da un altro sito/dominio o postman
public class VeicoloRestCtrl {

	@Autowired
	private IVeicoloService _service;

	/**
	 * Salvataggio di un veicolo con o senza un'immagine
	 * 
	 * @param veicolo : dati veicolo
	 * @param file    : immagine
	 */
	@PostMapping("/upload") // in alternativa usare @RequestPart
	public ResponseEntity<Veicolo> uploadVeicolo(Veicolo veicolo,
			@RequestParam(name = "image", required = false) MultipartFile file) {

		// salvo veicolo con o senza immagine
		_service.saveVeicolo(veicolo, file);

		return ResponseEntity.ok().body(veicolo);
	}

	/**
	 * Tutti i veicoli
	 */
	@GetMapping("/all")
	public ResponseEntity<List<Veicolo>> getListFiles() {

		List<Veicolo> listaVeicoli = _service.getAllVeicoli();
		return ResponseEntity.status(HttpStatus.OK).body(listaVeicoli);
	}

	/**
	 * Un veicolo in base all'id
	 * 
	 * @param id : id database
	 * @return veicolo
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Veicolo> getVeicoloById(@PathVariable int id) {

		Veicolo veicolo = _service.getVeicoloById(id);

		if (veicolo == null) // id non presente su database
			return ResponseEntity.badRequest().build();
		else
			return ResponseEntity.ok().body(veicolo);
	}

	/**
	 * Cancella veicolo in base all'id
	 * 
	 * @param id : del veicolo
	 * @return
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity<Veicolo> cancellaVeicolo(@PathVariable int id) {

		Veicolo trovato = _service.getVeicoloById(id);

		if (trovato == null) // id non presente su database
			return ResponseEntity.badRequest().build();
		else {
			// cancello veicolo
			_service.deleteVeicolo(trovato);
			return ResponseEntity.ok(trovato);
		}
	}

}
