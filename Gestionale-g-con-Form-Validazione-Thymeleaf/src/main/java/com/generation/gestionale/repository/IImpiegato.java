package com.generation.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gestionale.entity.Impiegato;

@Repository
public interface IImpiegato extends JpaRepository<Impiegato, Integer> {

}
