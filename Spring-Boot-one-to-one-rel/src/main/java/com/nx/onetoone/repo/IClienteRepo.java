package com.nx.onetoone.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nx.onetoone.entity.Cliente;

public interface IClienteRepo extends JpaRepository<Cliente, Integer> {

}
