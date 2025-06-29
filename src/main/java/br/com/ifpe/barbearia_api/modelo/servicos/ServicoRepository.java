package br.com.ifpe.barbearia_api.modelo.servicos;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> { //informando que estou herdando JPA para a tabela Servico e o tipo da chave primária

  
}

