package com.nx.onetoone.ctrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nx.onetoone.entity.Cliente;
import com.nx.onetoone.entity.Dipendente;
import com.nx.onetoone.entity.Utente;
import com.nx.onetoone.repo.IClienteRepo;
import com.nx.onetoone.repo.IDipendenteRepo;
import com.nx.onetoone.repo.IUtenteRepo;

@RestController
@RequestMapping("/")
public class TestCtrl {

	 @Autowired
	 IUtenteRepo uRepo;
	 
	 @Autowired
	 IDipendenteRepo dRepo;
	 
	 @Autowired
	 IClienteRepo cRepo;
	
	@GetMapping("test")
	public void radice() {
		// vedi UtenteTest
		List<Dipendente> dip = dRepo.findAll();
		List<Cliente> cli = cRepo.findAll();
		List<Utente> ut = uRepo.findAll();
	}
	
}
