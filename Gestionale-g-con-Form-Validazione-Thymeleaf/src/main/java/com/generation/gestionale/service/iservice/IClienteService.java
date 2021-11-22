package com.generation.gestionale.service.iservice;

import java.util.List;

import com.generation.gestionale.entity.Cliente;

public interface IClienteService {
	List<Cliente> findAll();
}
