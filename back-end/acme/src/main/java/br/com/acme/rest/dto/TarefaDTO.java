package br.com.acme.rest.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TarefaDTO {
	
	@NotNull(message = "Descrição é obrigatória.")
	private String descricao;
	
	@NotNull(message = "Data da tarefa é obrigatória.")
	private String data;
	
}
