package com.generation.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gestionale.entity.Articolo;

@Repository
public interface IArticoloRepo extends JpaRepository<Articolo, Integer> {

}
