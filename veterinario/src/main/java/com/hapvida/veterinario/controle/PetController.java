package com.hapvida.veterinario.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hapvida.veterinario.entidades.Animal;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pet")
public class PetController {

	@Autowired
	AnimalDAO animalDao;

	@GetMapping("/listar")
	public ResponseEntity<List<Animal>> getAllPets() {
		try {
			List<Animal> pets = new ArrayList<Animal>();
			animalDao.findAll().forEach(pets::add);
			
			if (pets.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(pets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Animal> getPetById(@PathVariable("id") long id) {
		Optional<Animal> petData = animalDao.findById(id);

		if (petData.isPresent()) {
			return new ResponseEntity<>(petData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Animal> createPet(@RequestBody Animal animal) {
		try {
			Animal _animal = animalDao.save(animal);
			return new ResponseEntity<>(_animal, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Animal> updatePet(@PathVariable("id") long id, @RequestBody Animal animal) {
		Optional<Animal> petData = animalDao.findById(id);

		if (petData.isPresent()) {
			Animal _animal = petData.get();
			_animal.setNome(animal.getNome());
			_animal.setEspecie(animal.getEspecie());
			_animal.setRaca(animal.getRaca());
			_animal.setDataNascimento(animal.getDataNascimento());
			return new ResponseEntity<>(animalDao.save(_animal), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/apagar/{id}")
	public ResponseEntity<HttpStatus> deletePetById(@PathVariable("id") long id) {
		try {
			animalDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
