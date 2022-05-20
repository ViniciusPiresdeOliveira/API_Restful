package com.residencia.academia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.service.InstrutorService;

@RestController
@RequestMapping("/instrutor")
public class InstrutorController {
	@Autowired
	InstrutorService instrutorService;
	
	@GetMapping
	public ResponseEntity<List<Instrutor>> findAllInstrutor(){
		List<Instrutor> instrutorList = instrutorService.listarTodos();
		return new ResponseEntity<>(instrutorList, HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Instrutor> findInstrutorById(Integer id) {
		Instrutor instrutor = instrutorService.listarUm(id);
		return new ResponseEntity<>(instrutor, HttpStatus.OK);
	}
}
