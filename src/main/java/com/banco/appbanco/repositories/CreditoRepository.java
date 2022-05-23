package com.banco.appbanco.repositories;

import com.banco.appbanco.entities.Credito;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, String> {
}
