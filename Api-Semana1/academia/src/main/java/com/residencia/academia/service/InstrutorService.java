package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.repository.InstrutorRepository;

@Service
public class InstrutorService {

	@Autowired
	InstrutorRepository instrutorRepository;
	
	public List<Instrutor> listarTodos() {
		return instrutorRepository.findAll();
	}
	
	public Instrutor listarUm (Integer id){
		return instrutorRepository.findById(id).get();
	}
}
