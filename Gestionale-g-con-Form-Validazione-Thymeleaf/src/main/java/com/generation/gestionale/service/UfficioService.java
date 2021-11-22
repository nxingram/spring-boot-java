package com.generation.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.gestionale.entity.Ufficio;
import com.generation.gestionale.repository.IUfficio;
import com.generation.gestionale.service.iservice.IUfficioService;

@Service
public class UfficioService implements IUfficioService {

	@Autowired
	private IUfficio _rUfficio;
	
	@Override
	public List<Ufficio> findAll() {
		return _rUfficio.findAll();
	}



}
