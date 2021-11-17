package com.hapvida.veterinario.controller;

import java.util.List;
import java.util.Optional;

import com.hapvida.veterinario.entity.Animal;
import com.hapvida.veterinario.repository.AnimalDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet")
public class PetController {

	@Autowired
	AnimalDAO animalDao;

	@GetMapping("/listall")
	public ResponseEntity<List<Animal>> listAll() {
		try {

			List<Animal> pets = animalDao.findAll();

			return new ResponseEntity<>(pets, HttpStatus.OK);

		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Animal> getPetById(@PathVariable("id") long id) {
		Optional<Animal> petData = animalDao.findById(id);

		if (petData.isPresent()) {
			return new ResponseEntity<>(petData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Animal> addPet(@RequestBody Animal animal) {
		try {
			Animal _animal = animalDao.save(animal);
			return new ResponseEntity<>(_animal, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Animal> updatePetById(@RequestBody Animal animal, @PathVariable("id") long id) {
		Optional<Animal> petData = animalDao.findById(id);

		if (petData.isPresent()) {
			Animal _animal = petData.get();
			_animal.setNome(animal.getNome());
			_animal.setEspecie(animal.getEspecie());
			_animal.setRaca(animal.getRaca());
			_animal.setDatanascimento(animal.getDatanascimento());
			_animal.setTutor(animal.getTutor());
			return new ResponseEntity<>(animalDao.save(_animal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deletePetById(@PathVariable("id") long id) {
		try {
			animalDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
