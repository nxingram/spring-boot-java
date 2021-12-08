package com.nx.springtutorial.rest;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@GetMapping
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
			// non ho trovato lo studente, restituisco 400 e studente vuoto per non mandare in errore la fetch
			return new ResponseEntity<Studente>(new Studente(), HttpStatus.BAD_REQUEST);	
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
			// non ho trovato lo studente, restituisco 400 e studente vuoto per non mandare in errore la fetch
			return new ResponseEntity<Studente>(new Studente(), HttpStatus.BAD_REQUEST);	
		}
	}

	// consumes e produces: sono opzionali
	// MediaType.APPLICATION_JSON_VALUE == "application/json"
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	public ResponseEntity<Studente> addOne(@RequestBody Studente s) { // @RequestBody indica che i dati json si trovano nel body della request
		
		// controllo se c'è già l'email, che lancerebbe errore perchè deve essere unique
		boolean emailExists = this.service.emailExists(s.getEmail());
		
		if(emailExists) { 
			// errore! non posso avere email duplicate
			return new ResponseEntity<Studente>(s, HttpStatus.BAD_REQUEST);						
		}else {
			// salvo, e restituisco lo studente con il nuovo campo id generato dal database
			s = this.service.addOne(s);
			return new ResponseEntity<Studente>(s, HttpStatus.OK);			
		}
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delOne(@PathVariable int id){	
		// verifico che l'id da cancellare sia effettivamente su db
		Studente studID = this.service.findByID(id);
		if(studID == null)
			return ResponseEntity.badRequest().build();	;	// id non presente su database, restituisco status 400
		
		// l'id è presente, cancello il record
		this.service.delOne(id);
		return ResponseEntity.ok().build();	// restituisco status 200
	}

	// consumes e produces: sono opzionali
	// MediaType.APPLICATION_JSON_VALUE == "application/json"
//	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = "application/json")
	@PutMapping
	public ResponseEntity<Studente> putOne(@RequestBody Studente s) { // @RequestBody indica che i dati json si trovano nel body della request
		
		Studente studID = this.service.findByID(s.getId());
		if(studID == null)
			return new ResponseEntity<Studente>(s, HttpStatus.BAD_REQUEST);	// id non presente su database
		
		// controllo se c'è già l'email, che lancerebbe errore perchè deve essere unique
		Studente studMail = this.service.findByEmail(s.getEmail());
		
		if(studMail != null && studMail.getId() != s.getId()) { // se mail è assegnata ad 1 altro utente
			// errore! non posso avere email duplicate
			return new ResponseEntity<Studente>(s, HttpStatus.BAD_REQUEST);						
		}else {
			// salvo, e restituisco lo studente con i campi aggiornati
			s = this.service.updateOne(s);
			return new ResponseEntity<Studente>(s, HttpStatus.OK);			
		}
		
	}
}
