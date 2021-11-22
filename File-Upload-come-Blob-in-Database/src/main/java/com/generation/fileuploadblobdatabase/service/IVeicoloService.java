package com.generation.fileuploadblobdatabase.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.generation.fileuploadblobdatabase.entity.Veicolo;

public interface IVeicoloService {
	
	Veicolo save(MultipartFile file, Veicolo veicolo) throws IOException;
	
	Veicolo getVeicoloById(int id);
	
	List<Veicolo> getAllVeicoli();
}
