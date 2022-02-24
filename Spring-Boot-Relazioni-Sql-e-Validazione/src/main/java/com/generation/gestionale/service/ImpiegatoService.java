package com.generation.gestionale.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.gestionale.entity.Impiegato;
import com.generation.gestionale.entity.Ufficio;
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
		Optional<Impiegato> optional = _rImpiegato.findById(id);
		if (optional.isPresent())
			return optional.get();	
		else
			return null;
	}

	@Override
	public void delImpiegato(Impiegato imp) {
		_rImpiegato.delete(imp);
	}

}
