package com.generation.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.gestionale.entity.OrdineDettaglio;
import com.generation.gestionale.repository.IOrdineDettaglioRepo;
import com.generation.gestionale.service.iservice.IOrdineDettaglioService;

@Service
public class OrdineDettaglioService implements IOrdineDettaglioService {

	@Autowired
	private IOrdineDettaglioRepo _rOrdineDettaglio;
	
	@Override
	public List<OrdineDettaglio> findAll() {
		return _rOrdineDettaglio.findAll();
	}

}
