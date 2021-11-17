package com.hapvida.veterinario.repository;

import com.hapvida.veterinario.entity.Animal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalDAO extends JpaRepository<Animal, Long> {
   
}