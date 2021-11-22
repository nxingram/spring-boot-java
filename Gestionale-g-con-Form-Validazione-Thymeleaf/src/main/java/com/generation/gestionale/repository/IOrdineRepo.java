package com.generation.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gestionale.entity.Ordine;

@Repository
public interface IOrdineRepo extends JpaRepository<Ordine, Integer> {

}
