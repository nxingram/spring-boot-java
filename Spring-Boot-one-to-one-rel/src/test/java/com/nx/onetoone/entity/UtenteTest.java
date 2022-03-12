package com.nx.onetoone.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.nx.onetoone.repo.IClienteRepo;
import com.nx.onetoone.repo.IDipendenteRepo;
import com.nx.onetoone.repo.IUtenteRepo;

@SpringBootTest
class UtenteTest {

	@Autowired
	IUtenteRepo uRepo;

	@Autowired
	IDipendenteRepo dRepo;

	@Autowired
	IClienteRepo cRepo;

	@BeforeEach
	@Transactional
	void loadData() {
		Utente u1 = new Utente("test@email.it", "testNome");
		Utente u2 = new Utente("test2@email.it", "testNome2");
		uRepo.save(u1);
		uRepo.save(u2);

		Dipendente d1 = new Dipendente("ufficio1", u1);
		dRepo.save(d1);

		Cliente c1 = new Cliente("indirizzo1", u2);
		cRepo.save(c1);
	}

	@Test
	@Transactional
	void test() {
		System.out.println("qui");

		List<Dipendente> dip = dRepo.findAll();
		List<Cliente> cli = cRepo.findAll();
		List<Utente> ut = uRepo.findAll();

		assertTrue(dip.size() > 0);
		assertEquals("ufficio1", dip.get(0).getUfficio());
		assertTrue(cli.size() > 0);
		assertEquals("indirizzo1", cli.get(0).getIndirizzo());
		assertTrue(ut.size() > 1);
		assertEquals("testNome", ut.get(0).getPassword());
		assertEquals("test2@email.it", ut.get(1).getEmail());
	}

	@AfterEach
	@Transactional
	void clearData() {
		cRepo.deleteAll();
		dRepo.deleteAll();
		uRepo.deleteAll();
	}

}
