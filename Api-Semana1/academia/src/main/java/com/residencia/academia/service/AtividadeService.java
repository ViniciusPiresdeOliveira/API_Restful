package com.residencia.academia.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.residencia.academia.dto.AtividadeDTO;
import com.residencia.academia.dto.TurmaDTO;
import com.residencia.academia.entity.Atividade;
import com.residencia.academia.entity.Turma;
import com.residencia.academia.repository.AtividadeRepository;

@Service
public class AtividadeService {

	@Autowired
	AtividadeRepository atividadeRepository;

	public List<Atividade> listarTodos() {
		return atividadeRepository.findAll();
	}

	public Atividade listarUm(Integer id) {
		return atividadeRepository.findById(id).isPresent() ? atividadeRepository.findById(id).get() : null;
	}

	public AtividadeDTO listarUmDTO(Integer id) {
		Atividade atividade = atividadeRepository.findById(id).isPresent() ? atividadeRepository.findById(id).get() : null;
		AtividadeDTO atividadeDTO = new AtividadeDTO();
		if (null != atividade) {
			atividadeDTO = converterEntidadeParaDTO(atividade);
		}
		return atividadeDTO;
	}

	public Atividade saveAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public AtividadeDTO saveAtividadeDTO(AtividadeDTO atividadeDTO) {
		Atividade atividade = converterDTOParaEntidade(atividadeDTO);
		Atividade novoAtividade = atividadeRepository.save(atividade);

		return converterEntidadeParaDTO(novoAtividade);
	}

	public Atividade updateAtividade(Atividade atividade) {
		return atividadeRepository.save(atividade);
	}

	public void deleteAtividade(Integer id) {
		Atividade atividade = atividadeRepository.findById(id).get();
		atividadeRepository.delete(atividade);
	}

	/*public void deleteAtividade(Atividade atividade) {
		atividadeRepository.delete(atividade);
	}*/

	private Atividade converterDTOParaEntidade(AtividadeDTO atividadeDTO) {
		Atividade atividade = new Atividade();
		atividade.setIdAtividade(atividadeDTO.getIdAtividade());
		atividade.setNomeAtividade(atividadeDTO.getNomeAtividade());

		return atividade;
	}

	private AtividadeDTO converterEntidadeParaDTO(Atividade atividade) {
		AtividadeDTO atividadeDTO = new AtividadeDTO();
		atividadeDTO.setNomeAtividade(atividade.getNomeAtividade());

		List<TurmaDTO> listTurmaDTO = new ArrayList<>();
		if (null != atividade.getTurmaList()) {
			for (Turma turma : atividade.getTurmaList()) {
				TurmaDTO turmaDTO = new TurmaDTO();
				turmaDTO.setDataFim(turma.getDataFim());
				turmaDTO.setDataInicio(turma.getDataInicio());
				turmaDTO.setDuracaoTurma(turma.getDuracaoTurma());
				turmaDTO.setHorarioTurma(turma.getHorarioTurma());
				turmaDTO.setIdTurma(turma.getIdTurma());

				listTurmaDTO.add(turmaDTO);
			}
			atividadeDTO.setTurmaDTOList(listTurmaDTO);
		}
		return atividadeDTO;
	}
}
