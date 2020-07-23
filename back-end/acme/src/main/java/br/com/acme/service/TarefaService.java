package br.com.acme.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.aspectj.apache.bcel.generic.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.acme.domain.entity.Tarefa;
import br.com.acme.domain.repository.TarefaRepository;
import br.com.acme.rest.dto.TarefaDTO;

@Service
public class TarefaService {

	@Autowired
	private TarefaRepository tarefaRepository;

	public Tarefa adicionarTarefa(@Valid TarefaDTO dto) {
		LocalDate data = dto.getData().isEmpty()
				? LocalDate.now()
				: LocalDate.parse(dto.getData(), DateTimeFormatter.ISO_OFFSET_DATE_TIME);
		
		Tarefa tarefa = Tarefa.builder()
			.checked(false)
			.createdOn(LocalDateTime.now())
			.data(data)
			.descricao(dto.getDescricao())
			.build();
		
		return tarefaRepository.save(tarefa);
	}
	
	@Transactional
	public Tarefa atualizarStatusTarefa(Long id) {
		return tarefaRepository.findById(id)
				.map(tarefa -> {
					tarefa.setChecked(!tarefa.isChecked());
					tarefa.setLastModify(LocalDateTime.now());
					return tarefaRepository.save(tarefa);
				}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada."));
	}
	
	@Transactional
	public void removerTarefa(Long id) {
		tarefaRepository.findById(id)
			.map(tarefa -> {
				tarefaRepository.deleteById(tarefa.getId());
				return Type.VOID;
			}).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tarefa não encontrada"));
	}
	
	public List<Tarefa> listarTarefas() {
		return tarefaRepository.findAll();
	}
	
}
