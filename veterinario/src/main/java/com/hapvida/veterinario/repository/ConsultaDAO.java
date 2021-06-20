package com.hapvida.veterinario.repository;

import com.hapvida.veterinario.entidades.Consulta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ConsultaDAO extends JpaRepository<Consulta, Long> {
    
}