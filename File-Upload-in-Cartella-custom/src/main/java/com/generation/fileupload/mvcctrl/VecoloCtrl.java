package com.generation.fileupload.mvcctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.generation.fileupload.entity.Veicolo;
import com.generation.fileupload.service.VeicoloService;

/**
 * Tutorial <br>
 * https://www.codejava.net/frameworks/spring-boot/spring-boot-file-upload-tutorial
 * 
 * Multipart Content-Type: https://www.w3.org/Protocols/rfc1341/7_2_Multipart.html *
 */
@Controller
@RequestMapping("/mvc/veicolo")
public class VecoloCtrl {

	@Autowired
    private VeicoloService _serv;
	
	@GetMapping
	public String index(Model model) {
		model.addAttribute("veicolo", new Veicolo());
		return "veicolo-form";
	}
	
	@PostMapping("/save")
	public String salvaVeicolo(Veicolo veicolo, @RequestParam(name = "image", required = false) MultipartFile multipartFile, Model model) {// in alternativa usare @RequestPart
		// 1) creo un veicolo vuoto da restituire (con l'id)
		// 2) controllo se è stata caricata un'immagine
		// 3) non è stata caricata una immagine, salvo comunque il veicolo
		// 4) altrim salvo il veicolo su database, e l'immagine in una cartella
		// 5) si può fare lo stesso percorso con un'api rest, e restituire solo il veicolo senza l'html

		//1
		Veicolo veicoloSalvato = new Veicolo(); // vuoto
		
		//2
		if(multipartFile == null || multipartFile.isEmpty()) {
			//3
			veicoloSalvato = _serv.saveVeicolo(veicolo);
			
		}else {
			//4
			veicoloSalvato = _serv.saveVeicolo(veicolo, multipartFile);			
		}
        
        //5
        model.addAttribute("veicolo", veicoloSalvato);
        
		return "file-salvato";
	}
	
}
