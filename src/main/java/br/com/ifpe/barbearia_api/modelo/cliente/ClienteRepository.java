package br.com.ifpe.barbearia_api.modelo.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { //informando que estou herdando JPA para a tabela cliente e o tipo da chave primária

  
}

