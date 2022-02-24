package com.generation.gestionale.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.generation.gestionale.entity.Cliente;
import com.generation.gestionale.entity.Ordine;
import com.generation.gestionale.service.iservice.IClienteService;
import com.generation.gestionale.service.iservice.IOrdineService;

@SpringBootTest
class ClienteServiceTest {
	
	@Autowired
	IClienteService srvC;
	
	@Autowired
	IOrdineService srvO;

	@Test
	void test() {
		List<Ordine> lista = srvO.findAll();
		Ordine ordine = lista.get(0);
		Cliente cliente = ordine.getCliente();
		cliente.toString();
		
	}

}
