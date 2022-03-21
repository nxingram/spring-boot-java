package com.generation.fileupload.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomProperties {

	// cartella dove salvare le immagini
	public static String basepath = "veicoloimg";
	
	// immagine di default del veicolo se non è stata caricata
	public static String defaultImg = "img/veicoloDefault.jpg";

	
	
}
