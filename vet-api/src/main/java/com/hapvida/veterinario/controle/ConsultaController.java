package com.hapvida.veterinario.controle;

import java.util.List;
import java.util.Optional;

import com.hapvida.veterinario.entidades.Consulta;
import com.hapvida.veterinario.repository.ConsultaDAO;

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
@RequestMapping("/api/consulta")
public class ConsultaController {

	@Autowired
	ConsultaDAO consultaDao;

	@GetMapping("/listar")
	public ResponseEntity<List<Consulta>> listar() {
		try {
			List<Consulta> consultas = consultaDao.findAll();

			return new ResponseEntity<>(consultas, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/{id}")
	public ResponseEntity<Consulta> getConsultaById(@PathVariable("id") long id) {
		Optional<Consulta> consultaData = consultaDao.findById(id);

		if (consultaData.isPresent()) {
			return new ResponseEntity<>(consultaData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/cadastrar")
	public ResponseEntity<Consulta> cadastrar(@RequestBody Consulta consulta) {
		try {
			Consulta _consulta = consultaDao.save(consulta);
			return new ResponseEntity<>(_consulta, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("atualizar/{id}")
	public ResponseEntity<Consulta> updateConsultaById(@PathVariable("id") long id, @RequestBody Consulta consulta) {
		Optional<Consulta> consultaData = consultaDao.findById(id);

		if (consultaData.isPresent()) {
			Consulta _consulta = consultaData.get();
			_consulta.setDataconsulta(consulta.getDataconsulta());
			_consulta.setStatus(consulta.getStatus());
			_consulta.setAnimal(consulta.getAnimal());
			_consulta.setVeterinario(consulta.getVeterinario());
			return new ResponseEntity<>(consultaDao.save(_consulta), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/apagar/{id}")
	public ResponseEntity<HttpStatus> deleteConsultaById(@PathVariable("id") long id) {
		try {
			consultaDao.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
