package com.generation.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.gestionale.entity.Articolo;
import com.generation.gestionale.repository.IArticoloRepo;
import com.generation.gestionale.service.iservice.IArticoloService;

@Service
public class ArticoloService implements IArticoloService {

	@Autowired
	private IArticoloRepo _rArticolo;
	
	@Override
	public List<Articolo> findAll() {
		return _rArticolo.findAll();
	}

}
