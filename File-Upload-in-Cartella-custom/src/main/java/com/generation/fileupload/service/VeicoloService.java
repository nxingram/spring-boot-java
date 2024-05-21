package com.generation.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.generation.fileupload.config.CustomProperties;
import com.generation.fileupload.entity.Veicolo;
import com.generation.fileupload.repo.IVeicoloRepo;
import com.generation.fileupload.util.FileUploadUtil;

@Service
public class VeicoloService implements IVeicoloService {
	
	@Autowired
    private IVeicoloRepo _repo;
	
	 public Veicolo saveVeicolo(Veicolo veicolo, MultipartFile multipartFile) {
		 
		// controllo se è stata caricata un'immagine
		if(multipartFile == null || multipartFile.isEmpty()) {
			// non è stata caricata una immagine, salvo comunque il veicolo
			_repo.save(veicolo);
			
			// ritorno al controller
			return veicolo;		
		}
		 
		// c'è 1 immagine da salvare oltre ai dati del veicolo
		// nome del file o immagine: **rimuovo spazi**
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename().strip().replace(" ", "-"));
		
		// setto nome del file prima di salvare il veicolo
		veicolo.setFileName(fileName);		
		// salvo il veicolo
		_repo.save(veicolo);

		// genero il percorso della cartella dove salvare l'immagine
		String uploadDir = CustomProperties.IMG_SAVE_PATH + "/" + veicolo.getId();
		 
        try {
			// converte percorso stringa in un path
			Path uploadPath = Paths.get(uploadDir);

			if (!Files.exists(uploadPath)) {
				// crea cartella dove salvare l'immagine se non esiste
				Files.createDirectories(uploadPath); // throws IOException
			}
			try (InputStream inputStream = multipartFile.getInputStream()) { // try with resource
				Path filePath = uploadPath.resolve(fileName); // percorso file completo
				// sovrascrive file se già presente con stesso nome
				Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);


			} catch (IOException ioe) {
				throw new IOException("Could not save image file: " + fileName, ioe);
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
        

        // restituisco il veicolo salvato
		return veicolo;
		
	}


	@Override
	public List<Veicolo> getAllVeicoli() {
		return _repo.findAll();
	}

	@Override
	public Veicolo getVeicoloById(int id) {
		Optional<Veicolo> optional = _repo.findById(id);
		if(optional.isPresent())
			return optional.get();
		else
			return null;
	}

	@Override
	public void deleteVeicolo(Veicolo trovato) {
		try {
			// cancello immagini e cartella

			String dir = CustomProperties.IMG_SAVE_PATH + "/" + trovato.getId();
			
			if (Files.exists(Paths.get(dir))) {
				// N.B. aggiungere nel pom.xml la dipendenza "commons-io"
				// <!-- https://mvnrepository.com/artifact/commons-io/commons-io -->
				FileUtils.deleteDirectory(new File(dir));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		// cancello veicolo
		_repo.delete(trovato);		
		
	}


}
