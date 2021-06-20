package com.hapvida.veterinario.repository;

import com.hapvida.veterinario.entidades.Veterinario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface VeterinarioDAO extends JpaRepository<Veterinario, Long> {

}