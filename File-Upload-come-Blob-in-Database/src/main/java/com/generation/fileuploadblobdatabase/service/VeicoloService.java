package com.generation.fileuploadblobdatabase.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.generation.fileuploadblobdatabase.entity.Veicolo;
import com.generation.fileuploadblobdatabase.repo.IVeicoloRepo;

@Service
public class VeicoloService implements IVeicoloService {

	@Autowired
	private IVeicoloRepo _repo;
	
	@Override
	public Veicolo save(MultipartFile file, Veicolo veicolo) throws IOException {
		// 1) nome del file caricato
		// 2) setto i campi con le informazioni del file e il blob del file
		// 3) salvo e restituisco il veicolo

		//1
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		//2
		veicolo.setFileName(fileName);
		veicolo.setType(file.getContentType());
		veicolo.setData(file.getBytes()); // blob immagine
		
		//3
		return _repo.save(veicolo);
	}

	@Override
	public Veicolo getVeicoloById(int id) {
		return _repo.findById(id).get();
	}

	@Override
	public List<Veicolo> getAllVeicoli() {
		return _repo.findAll();
	}

}
