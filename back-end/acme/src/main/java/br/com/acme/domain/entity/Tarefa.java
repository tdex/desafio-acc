package br.com.acme.domain.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Tarefa {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Descrição da tarefa é obrigatório.")
	private String descricao;
	
	@NotNull(message = "Data da tarefa é obrigatório.")
	private LocalDate data;
	
	private LocalDateTime createdOn;
	
	private LocalDateTime lastModify;
	
	private boolean checked;
	
}
