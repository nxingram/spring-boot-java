package com.nx.springtutorial.service;

import java.util.List;

import com.nx.springtutorial.entity.Studente;

public interface StudenteService {

	// metodi da implementare nel service impl
	List<Studente> findAll();
	Studente findByID(int id);
}
