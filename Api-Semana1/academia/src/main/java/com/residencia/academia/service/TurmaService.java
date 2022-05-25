package com.residencia.academia.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.InstrutorDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Instrutor;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.TurmaRepository;

@Service
public class TurmaService {

	@Autowired
	TurmaRepository turmaRepository;
	@Autowired
	InstrutorService instrutorService;
	@Autowired
	AtividadeService atividadeService;

	public List<Turma> listarTodos() {
		return turmaRepository.findAll();
	}

	public Turma listarUma(Integer id) {
		return turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
	}

	public TurmaDTO listarUmDTO(Integer id) {
		Turma turma = turmaRepository.findById(id).isPresent() ? turmaRepository.findById(id).get() : null;
		TurmaDTO turmaDTO = new TurmaDTO();
		if (null != turma) {
			turmaDTO = converterEntidadeParaDTO(turma);
		}
		return turmaDTO;
	}

	public Turma saveTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public TurmaDTO saveTurmaDTO(TurmaDTO turmaDTO) {
		Turma turma = converterDTOParaEntidade(turmaDTO);
		Turma novoTurma = turmaRepository.save(turma);

		return converterEntidadeParaDTO(novoTurma);
	}

	public Turma updateTurma(Turma turma) {
		return turmaRepository.save(turma);
	}

	public void deleteTurma(Integer id) {
		turmaRepository.deleteById(id);
	}

	private Turma converterDTOParaEntidade(TurmaDTO turmaDTO) {
		Turma turma = new Turma();
		turma.setDataFim(turmaDTO.getDataFim());
		turma.setDataInicio(turmaDTO.getDataInicio());
		turma.setDuracaoTurma(turmaDTO.getDuracaoTurma());
		turma.setHorarioTurma(turmaDTO.getHorarioTurma());
		turma.setIdTurma(turmaDTO.getIdTurma());
		Instrutor instrutor = instrutorService.listarUm(turmaDTO.getInstrutorDTO().getIdInstrutor());
		turma.setInstrutor(instrutor);
		Atividade atividade = atividadeService.listarUm(turmaDTO.getInstrutorDTO().getIdInstrutor());
		turma.setAtividade(atividade);

		return turma;
	}

	private TurmaDTO converterEntidadeParaDTO(Turma turma) {
		TurmaDTO turmaDTO = new TurmaDTO();
		turmaDTO.setDataFim(turma.getDataFim());
		turmaDTO.setDataInicio(turma.getDataInicio());
		turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
		turmaDTO.setHorarioTurma(turma.getHorarioTurma());
		turmaDTO.setIdTurma(turma.getIdTurma());
		InstrutorDTO instrutorDTO = instrutorService.listarUmDTO(turma.getInstrutor().getIdInstrutor());
		turmaDTO.setInstrutorDTO(instrutorDTO);
		AtividadeDTO atividadeDTO = atividadeService.listarUmDTO(turma.getInstrutor().getIdInstrutor());
		turmaDTO.setAtividadeDTO(atividadeDTO);
		return turmaDTO;

	}
	/*
	 * public Boolean deleteTurmaComConferencia(Integer id) {
	 * if(turmaRepository.findById(id).isPresent()) {
	 * turmaRepository.deleteById(id); return true; }else { return false; }
	 * 
	 * }
	 */
}
