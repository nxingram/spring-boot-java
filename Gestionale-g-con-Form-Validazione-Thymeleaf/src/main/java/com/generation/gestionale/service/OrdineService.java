package com.generation.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.gestionale.entity.Ordine;
import com.generation.gestionale.repository.IOrdineRepo;
import com.generation.gestionale.service.iservice.IOrdineService;

@Service
public class OrdineService implements IOrdineService {

	@Autowired
	private IOrdineRepo _rOrdine;
	
	@Override
	public List<Ordine> findAll() {
		return _rOrdine.findAll();
	}

	
}
