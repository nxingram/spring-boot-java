package com.nx.springtutorial.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nx.springtutorial.entity.Studente;

/*
 * ATTENZIONE:
 * ricordarsi di aggiunge la dipendency Data Jpa e MySql Driver
 * altrimenti non trovo l'interfaccia JpaRepository
 * e non si connette al database
 */

 /**
  * StudenteRepository: corrisponde a StudenteDAO.
  * 
  * JpaRepository: interfaccia spring che fornisce metodi crud di base.
  * 
  * Studente: l'entità corrispondente sul database.
  * 
  * Integer: Tipo della chiave primaria dell'entità
  * deve essere una classe, quindi non posso usare int primitivo, devo usare la classe wrapper Integer.
  */
public interface StudenteRepository extends JpaRepository<Studente, Integer> {

	// eventuali metodi custom
}
