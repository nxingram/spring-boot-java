package com.generation.fileuploadblobdatabase.restctrl;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.generation.fileuploadblobdatabase.dto.MessaggioDto;
import com.generation.fileuploadblobdatabase.dto.VeicoloDto;
import com.generation.fileuploadblobdatabase.entity.Veicolo;
import com.generation.fileuploadblobdatabase.service.IVeicoloService;


/**
 * https://www.bezkoder.com/spring-boot-upload-file-database/
 * REST
 * Usare Postman o simile per effettuare le requests di prova
 * 
 * Multipart Content-Type: https://www.w3.org/Protocols/rfc1341/7_2_Multipart.html
 */
@RestController
@RequestMapping("/api/veicolo")
@CrossOrigin("http://localhost:8081") // necessario se la request proviene da un altro sito/dominio o postman
public class VeicoloRestCtrl {

	@Autowired
	private IVeicoloService _service;
	
	/**
	 * Salvataggio di un veicolo con un'immagine
	 * @param file : immagine
	 * @param vDto : dati veicolo
	 * @return messaggio
	 */
	@PostMapping("/upload")
	public ResponseEntity<MessaggioDto> uploadVeicolo(Veicolo veicolo, @RequestParam("file") MultipartFile file){
		
		// 1) salvo il veicolo con l'immagine su db
		// 2) se salvato correttamente, restituisco un messaggio ok
		// 3) altrimenti restituisco un messaggio di errore

		String message = "";
		
		try {
			//1
			_service.save(file, veicolo);			
			message = "Veicolo salvato correttamente: " + file.getOriginalFilename();
			
			//2
			return ResponseEntity.status(HttpStatus.OK).body(new MessaggioDto(message));
			
		} catch (IOException e) {
			e.printStackTrace(); // stampa errore in console java
			message = "Salvataggio veicolo non ruscito: " + file.getOriginalFilename() + "!";
			//3
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessaggioDto(message));
		}
		
	}
	
	/**
	 * Tutti i veicoli
	 * @return lista di veicoli con il percorso dell'immagine
	 */
	@GetMapping("/all")
	public ResponseEntity<List<VeicoloDto>> getListFiles(){
		
		// 1) prendo tutti i veicoli
		// 2) Opzionale: trasformo una lista di veicoli, in una lista di veicoliDto
		// (oppure restituire direttamnte la lista, senza mapparlo su un dto => vai direttamente a 6)
		
		// 3) genero il percorso per scaricare l'immagine chimando la rotta /files/id
		// 4) creo il VeicoloDto con dentro il percorso del file,	
		// per non mandare tutte le immagini che peserebbero troppo 
		
		// 5) restituisco a map il veicolo mappato sul dto
		// 6) restituisco la lista dei veicoliDto

		//1
		List<Veicolo> listaVeicoli = _service.getAllVeicoli();
		
		//2
		List<VeicoloDto> listaVeicoliDto = listaVeicoli.stream().map(veicolo -> {
			
			//3
			String fileDownloadUri = ServletUriComponentsBuilder
					.fromCurrentContextPath()
					.path("/api/veicolo/")
					.path(String.valueOf(veicolo.getId()))  // percorso del metodo getDetattaglioVeicoloById
					.toUriString();
						
			//4
			VeicoloDto vDto = new VeicoloDto();
			vDto.setId(veicolo.getId());
			vDto.setName(veicolo.getName());
			vDto.setFileName(veicolo.getFileName());
			vDto.setType(veicolo.getType());
			vDto.setUrl(fileDownloadUri);
			vDto.setSize(veicolo.getData().length);
			
			//5
			return vDto;
			
		} ).collect(Collectors.toList()); // trasforma lo stream map in lista
		
		//6
		return ResponseEntity.status(HttpStatus.OK).body(listaVeicoliDto);
	}
	
	/**
	 * Restituisce Dettaglio del veicolo con l'immagine
	 * @param id del veicolo
	 * @return veicolo
	 */
	@GetMapping("/{id}")
	public ResponseEntity<Veicolo> getDetattaglioVeicoloById(@PathVariable int id){
		// 1) prendo il veicolo 
		// 2) restituisco il veicolo con all'interno l'immagine
		
		//1
		Veicolo veicolo = _service.getVeicoloById(id);		
		//2
		return ResponseEntity.ok().body(veicolo);
	}
	
	/**
	 * Restituisce l'immagine
	 * @param id del veicolo
	 * @return immagine
	 * https://developer.mozilla.org/en-US/docs/Web/HTTP/Headers/Content-Disposition
	 */
	@GetMapping("/immagine/{id}")
	public ResponseEntity<byte[]> downloadImmagine(@PathVariable int id){
		// 1) prendo il veicolo 
		// 2) restituisco l'immagine del veicolo
		
		//1
		Veicolo veicolo = _service.getVeicoloById(id);		
		//2
		return ResponseEntity
				.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + veicolo.getFileName() + "\"") // fa scaricare l'immagine
				.body(veicolo.getData());
	}
	
	
	
}
