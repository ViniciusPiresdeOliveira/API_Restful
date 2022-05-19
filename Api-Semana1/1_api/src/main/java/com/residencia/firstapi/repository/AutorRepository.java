package com.residencia.firstapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.residencia.firstapi.entity.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

	Autor findByAutorNomeIgnoreCase(String nome);
}
