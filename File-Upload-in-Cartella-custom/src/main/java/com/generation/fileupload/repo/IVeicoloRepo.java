package com.generation.fileupload.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.generation.fileupload.entity.Veicolo;


@Repository
public interface IVeicoloRepo extends JpaRepository<Veicolo, Integer> {

}
