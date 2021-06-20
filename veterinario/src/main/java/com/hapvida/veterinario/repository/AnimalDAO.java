package com.hapvida.veterinario.repository;


import com.hapvida.veterinario.entidades.Animal;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnimalDAO extends JpaRepository<Animal, Long> {

    //List<Animal> findByNameContaining(String nome);
   
}