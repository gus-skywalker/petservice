package com.hapvida.veterinario.repository;

import com.hapvida.veterinario.entity.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaDAO extends JpaRepository<Consulta, Long> {
    
}