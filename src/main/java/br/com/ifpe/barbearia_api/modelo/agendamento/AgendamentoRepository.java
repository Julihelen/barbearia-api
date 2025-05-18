package br.com.ifpe.barbearia_api.modelo.agendamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> { //informando que estou herdando JPA para a tabela agenda e o tipo da chave primária

  
}

