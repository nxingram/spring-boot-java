package com.generation.gestionale.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.gestionale.entity.Impiegato;
import com.generation.gestionale.repository.IImpiegato;
import com.generation.gestionale.service.iservice.IImpiegatoService;

@Service
public class ImpiegatoService implements IImpiegatoService {

	@Autowired
	private IImpiegato _rImpiegato;
	
	@Override
	public List<Impiegato> findAll() {
		return _rImpiegato.findAll();
	}

	@Override
	public Impiegato save(Impiegato i) {
		return _rImpiegato.save(i);
	}
	
	@Override
	public Impiegato findByID(Integer id) {
		try {			
			return _rImpiegato.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	@Override
	public void delImpiegato(Impiegato imp) {
		_rImpiegato.delete(imp);		
	}

	
	

}
