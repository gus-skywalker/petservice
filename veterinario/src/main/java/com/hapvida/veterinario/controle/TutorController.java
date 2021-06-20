package com.hapvida.veterinario.controle;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.hapvida.veterinario.entidades.Tutor;
import com.hapvida.veterinario.repository.TutorDAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tutor")
public class TutorController {

	@Autowired
	TutorDAO tutorDao;

	@GetMapping("/listar")
	public ResponseEntity<List<Tutor>> lista() {
		try {

			List<Tutor> tutores = new ArrayList<Tutor>();
			tutorDao.findAll().forEach(tutores::add);

			return new ResponseEntity<>(tutores, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Tutor> getTutorById(@PathVariable("id") long id) {
		Optional<Tutor> tutorData = tutorDao.findById(id);

		if (tutorData.isPresent()) {
			return new ResponseEntity<>(tutorData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Tutor> adiciona(@RequestBody Tutor tutor) {
		try {
			Tutor _tutor = tutorDao.save(new Tutor(tutor.getNome(), tutor.getTelefone(), tutor.getEmail()));
			return new ResponseEntity<>(_tutor, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/apagar/{id}")
	public ResponseEntity<HttpStatus> deleteTutorById(@PathVariable("id") long id) {
		try {
			tutorDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}