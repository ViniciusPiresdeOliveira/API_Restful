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

import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.exception.NoSuchElementFoundException;
import com.residencia.academia.service.AtividadeService;

@RestController
@RequestMapping("/atividade")
public class AtividadeController {

	@Autowired
	private AtividadeService atividadeService;

	@GetMapping
	public ResponseEntity<List<Atividade>> findAllAtividade() {
		List<Atividade> atividadeList = atividadeService.listarTodos();
		return new ResponseEntity<>(atividadeList, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Atividade> findAtividadeById(@PathVariable Integer id) {
		Atividade atividade = atividadeService.listarUm(id);
		if (null == atividade)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(atividade, HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Atividade> saveAtividade(@RequestBody Atividade atividade) {
		Atividade novaAtividade = atividadeService.saveAtividade(atividade);
		if (null == atividade)
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(novaAtividade, HttpStatus.OK);
	}

	@PutMapping
	public ResponseEntity<Atividade> updateAtividade(@RequestBody Atividade atividade) {
		Atividade novaAtividade = atividadeService.updateAtividade(atividade);
		return new ResponseEntity<>(novaAtividade, HttpStatus.OK);

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAtividade(@PathVariable Integer id) {
		Atividade atividade = atividadeService.listarUm(id);
		if (null == atividade)
			throw new NoSuchElementFoundException(
					"Não foi possível excluir a Turma, " + "pois não foi " + "encontrada uma turma com o id " + id);

		atividadeService.deleteAtividade(id);
		return new ResponseEntity<>("", HttpStatus.OK);
	}

}
