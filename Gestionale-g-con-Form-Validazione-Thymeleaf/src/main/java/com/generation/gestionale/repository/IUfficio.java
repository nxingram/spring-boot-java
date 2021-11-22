package com.generation.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gestionale.entity.Ufficio;

@Repository
public interface IUfficio extends JpaRepository<Ufficio, Integer> {

}
