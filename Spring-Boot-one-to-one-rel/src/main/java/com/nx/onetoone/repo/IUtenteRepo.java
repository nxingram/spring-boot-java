package com.nx.onetoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nx.onetoone.entity.Utente;

public interface IUtenteRepo extends JpaRepository<Utente, Integer> {

}
