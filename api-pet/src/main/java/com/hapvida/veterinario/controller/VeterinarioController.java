package com.hapvida.veterinario.controller;

import java.util.List;
import java.util.Optional;

import com.hapvida.veterinario.entity.Veterinario;
import com.hapvida.veterinario.repository.VeterinarioDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vet")
@CrossOrigin("*")
public class VeterinarioController {

	@Autowired
	VeterinarioDAO vetDao;    

    @GetMapping("/listall")
    public ResponseEntity<List<Veterinario>> listAll() {
		try {
            List<Veterinario> vets = vetDao.findAll();

			return new ResponseEntity<>(vets, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

    @GetMapping("/{id}")
	public ResponseEntity<Veterinario> getVetById(@PathVariable("id") long id) {
		Optional<Veterinario> vetData = vetDao.findById(id);

		if (vetData.isPresent()) {
			return new ResponseEntity<>(vetData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
    }

	@PostMapping("/")
    public ResponseEntity<Veterinario> addVet(@RequestBody Veterinario veterinario) {
		try {
			Veterinario _veterinario = vetDao
					.save(veterinario);
			return new ResponseEntity<>(_veterinario, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/{id}")
	public ResponseEntity<Veterinario> updateVetById(@RequestBody Veterinario veterinario, @PathVariable("id") long id) {
		Optional<Veterinario> vetData = vetDao.findById(id);

		if (vetData.isPresent()) {
			Veterinario _veterinario = vetData.get();
			_veterinario.setNome(veterinario.getNome());
			_veterinario.setTelefone(veterinario.getTelefone());
			_veterinario.setEmail(veterinario.getEmail());
			return new ResponseEntity<>(vetDao.save(_veterinario), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

    @DeleteMapping("/{id}")
	public ResponseEntity<HttpStatus> deleteVetById(@PathVariable("id") long id) {
		try {
			vetDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
   
}