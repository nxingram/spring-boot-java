package com.generation.fileupload.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.generation.fileupload.entity.Veicolo;

public interface IVeicoloService {

	Veicolo saveVeicolo(Veicolo veicolo, MultipartFile multipartFile);
	Veicolo saveVeicolo(Veicolo veicolo);
	List<Veicolo> getAllVeicoli();
	Veicolo getVeicoloById(int id);
	void deleteVeicolo(Veicolo trovato);
}
