package com.hapvida.veterinario.controller;

import java.util.List;
import java.util.Optional;

import com.hapvida.veterinario.entity.Tutor;
import com.hapvida.veterinario.repository.TutorDAO;

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
@RequestMapping("/api/tutor")
public class TutorController {

	@Autowired
	TutorDAO tutorDao;

	@GetMapping("/listall")
	public ResponseEntity<List<Tutor>> listAll() {
		try {

			List<Tutor> tutores = tutorDao.findAll();

			return new ResponseEntity<>(tutores, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tutor> getTutorById(@PathVariable("id") long id) {
		Optional<Tutor> tutorData = tutorDao.findById(id);

		if (tutorData.isPresent()) {
			return new ResponseEntity<>(tutorData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Tutor> addTutor(@RequestBody Tutor tutor) {
		try {
			Tutor _tutor = tutorDao.save(tutor);
			return new ResponseEntity<>(_tutor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Tutor> updatePetById(@RequestBody Tutor tutor, @PathVariable("id") long id) {
		Optional<Tutor> tutorData = tutorDao.findById(id);

		if (tutorData.isPresent()) {
			Tutor _tutor = tutorData.get();
			_tutor.setNome(tutor.getNome());
			_tutor.setTelefone(tutor.getTelefone());
			_tutor.setEmail(tutor.getEmail());
			return new ResponseEntity<>(tutorDao.save(_tutor), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<HttpStatus> deleteTutorById(@PathVariable("id") long id) {
		try {
			tutorDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}