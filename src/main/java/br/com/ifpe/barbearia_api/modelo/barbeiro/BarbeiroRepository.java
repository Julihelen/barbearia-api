package br.com.ifpe.barbearia_api.modelo.barbeiro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> { //informando que estou herdando JPA para a tabela barbeiro e o tipo da chave primária

  
}

