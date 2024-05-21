package com.generation.fileupload.mvcctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
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
	
	@PostMapping("/save") // in alternativa usare "@RequestParam"
	public String salvaVeicolo(Veicolo veicolo, @RequestPart(name = "image", required = false) MultipartFile multipartFile, Model model) {
	
		// salvo veicolo con o senza immagine
		_serv.saveVeicolo(veicolo, multipartFile);			
        
        // si può fare lo stesso percorso con un'api rest, e restituire solo il veicolo senza l'html
        model.addAttribute("veicolo", veicolo);
        
		return "file-salvato";
	}
	
}
