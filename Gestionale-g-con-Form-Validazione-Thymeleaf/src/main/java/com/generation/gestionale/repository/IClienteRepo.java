package com.generation.gestionale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.gestionale.entity.Cliente;

@Repository
public interface IClienteRepo extends JpaRepository<Cliente, Integer> {

}
