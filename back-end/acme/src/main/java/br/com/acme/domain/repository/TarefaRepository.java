package br.com.acme.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.acme.domain.entity.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
