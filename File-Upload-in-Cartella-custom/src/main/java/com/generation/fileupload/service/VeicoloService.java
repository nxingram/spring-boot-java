package com.generation.fileupload.service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.apache.tomcat.util.http.fileupload.FileUtils;
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
		 
		 // 1) nome del file o immagine
		 // 2) setto nome del file prima di salvare il veicolo
		 // 3) salvo il veicolo
		 // 4) genero il percorso della cartella dove salvare l'immagine
		 // 5) classe utility con metodo statico che salva il file
		 // 6) restituisco il veicolo salvato
		
		//1
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		
		//2
		veicolo.setFileName(fileName);
		
		//3
		Veicolo veicoloSalvato = _repo.save(veicolo);

		//4
		String uploadDir = CustomProperties.basepath + "/" + veicoloSalvato.getId();
		 
        try {
        	//5
			FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
        

        //6
		return veicoloSalvato;
		
	}

	@Override
	public Veicolo saveVeicolo(Veicolo veicolo) {
		return _repo.save(veicolo);
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
		String url =  "/" + CustomProperties.basepath + "/" + trovato.getId();
		
		// cancello veicolo
		_repo.delete(trovato);
		
		// cancello immagini
		try {
			// org.apache.tomcat.util.http.fileupload.FileUtils;
			// non funziona, da verificare
			FileUtils.deleteDirectory(Paths.get(url).toFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
				
		
	}


}
