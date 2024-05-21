package com.generation.fileupload.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomProperties {

	// cartella dove salvare le immagini

	public static final String IMG_SAVE_PATH = "src/main/resources/static/veicoloimg";
	public static final String IMG_URL_PATH = "veicoloimg";
	
	// immagine di default del veicolo se non è stata caricata
	public static final String DEFAULT_IMG_PATH = "img/veicoloDefault.jpg";

	
	
}
