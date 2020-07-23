package br.com.acme.rest.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.acme.domain.entity.Tarefa;
import br.com.acme.rest.dto.TarefaDTO;
import br.com.acme.service.TarefaService;

@RestController
@RequestMapping("/api/tarefa")
public class TarefaController {
	
	@Autowired
	private TarefaService service;

	@GetMapping
	public List<Tarefa> listarTarefas() {
		return service.listarTarefas();
	}
	
	@PostMapping
	public Tarefa addTarefa(@RequestBody @Valid TarefaDTO dto) {
		return service.adicionarTarefa(dto);
	}
	
	@PatchMapping("{id}")
	public Tarefa atualizarStatusTarefa(@PathVariable Long id) {
		return service.atualizarStatusTarefa(id);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removerTarefa(@PathVariable Long id) {
		service.removerTarefa(id);
	}
	
}
