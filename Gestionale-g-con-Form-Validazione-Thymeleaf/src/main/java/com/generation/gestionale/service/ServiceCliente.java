package com.generation.gestionale.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.generation.gestionale.entity.Cliente;
import com.generation.gestionale.repository.IClienteRepo;
import com.generation.gestionale.service.iservice.IClienteService;

@Service
public class ServiceCliente implements IClienteService {
	
	@Autowired
	private IClienteRepo _rCliente;
	
	@Override
	public List<Cliente> findAll() {
		return _rCliente.findAll();
	}

}
