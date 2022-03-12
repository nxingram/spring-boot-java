package com.nx.onetoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nx.onetoone.entity.Dipendente;

public interface IDipendenteRepo extends JpaRepository<Dipendente, Integer> {

}
