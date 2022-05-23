package com.banco.appbanco.repositories;

import com.banco.appbanco.entities.Linea;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LineaRepository extends JpaRepository<Linea, String> {
}
