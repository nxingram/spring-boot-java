package com.nx.springtutorial.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nx.springtutorial.entity.Studente;
import com.nx.springtutorial.repo.StudenteRepository;

@Service // business layer: l'annotatazione serve a spring boot per l'autowired nel
			// controller
public class StudenteServiceImpl implements StudenteService {

	@Autowired // viene instanziato automaticamente da spring ( come fare = new
				// StudenteRpository() )
	private StudenteRepository studenteRepo;

	@Override
	public List<Studente> findAll() {
		return this.studenteRepo.findAll();
	}

	@Override
	public Studente findByID(int id) {
//		ATTENZIONE!: NON USARE GET_BY_ID!
		
		Optional<Studente> optional = this.studenteRepo.findById(id); // restituisce un Optional, con dentro il valore, se esiste

		if (optional.isPresent()) { // controlle se all'interno c'è 1 valore
			return optional.get(); // estraggo il valore
		} else {
			return null; // altrimenti restituisco null
		}
	}

}
