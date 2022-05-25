package com.residencia.academia.controller;

import java.util.List;

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

import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.TurmaService;

@RestController
@RequestMapping("/turma")
public class TurmaController {

	@Autowired
	private TurmaService turmaService;

	@GetMapping
	public ResponseEntity<List<Turma>> findAllTurma() {
		List<Turma> turmaList = turmaService.listarTodos();
		if (turmaList.isEmpty()) {
			throw new NoSuchElementFoundException("Lista vazia");
		} else {
			return new ResponseEntity<>(turmaList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Turma> findTurmaById(@PathVariable Integer id) {
		Turma turma = turmaService.listarUma(id);
		if (null == turma)
			throw new NoSuchElementFoundException("Não foi encontrada Turma com o ID: " + id);
		else
			return new ResponseEntity<>(turmaService.listarUma(id), HttpStatus.OK);
	}
	
	@GetMapping("/dto/{id}")
	public ResponseEntity<TurmaDTO> findTurmaDTOById(@PathVariable Integer id) {
		TurmaDTO turmaDTO = turmaService.listarUmDTO(id);
		if (null == turmaDTO)
			throw new NoSuchElementFoundException("Não foi encontrada Turma com o ID: " + id);
		else
			return new ResponseEntity<>(turmaService.listarUmDTO(id), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Turma> saveTurma(@RequestBody Turma turma) {
		Turma novaTurma = turmaService.saveTurma(turma);
		return new ResponseEntity<>(turmaService.saveTurma(turma), HttpStatus.CREATED);
	}
	
	@PostMapping("/dto")
	public ResponseEntity<TurmaDTO> saveTurmaDTO(@RequestBody TurmaDTO turmaDTO) {
		TurmaDTO novaTurmaDTO = turmaService.saveTurmaDTO(turmaDTO);
		return new ResponseEntity<>(novaTurmaDTO, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<Turma> updateTurma(@RequestBody Turma turma) {
		Turma novaTurma = turmaService.updateTurma(turma);
		if (null == novaTurma) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(novaTurma, HttpStatus.OK);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTurma(@PathVariable Integer id) {
		Turma turma = turmaService.listarUma(id);
		if (null == turma) {
			throw new NoSuchElementFoundException("Não foi possível excluir a Turma, pois não "
												 +"foi encontrada uma turma com o ID: " + id);
		} else {
			turmaService.deleteTurma(id);
			return new ResponseEntity<>("Deletado com sucesso", HttpStatus.OK);
		}
	}

	/*
	 * @DeleteMapping("/{id}") public ResponseEntity<String>
	 * deleteTurmaComConferencia(@PathVariable I /*
	 * 
	 * @DeleteMapping("/{id}") public ResponseEntity<String>
	 * deleteTurmaComConferencia(@PathVariable Integer id) { Boolean verificacao =
	 * turmaService.deleteTurmaComConferencia(id); if(verificacao) return new
	 * ResponseEntity<>("", HttpStatus.OK); else throw new
	 * NoSuchElementFoundException("Não foi possível excluir a Turma, " +
	 * "pois não foi " + "encontrada uma turma com o id " + id);
	 * 
	 * }
	 */
}
