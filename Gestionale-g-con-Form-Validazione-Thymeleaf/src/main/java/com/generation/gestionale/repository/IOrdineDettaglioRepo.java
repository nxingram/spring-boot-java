package com.generation.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gestionale.entity.OrdineDettaglio;

@Repository
public interface IOrdineDettaglioRepo extends JpaRepository<OrdineDettaglio, Integer> {

}
