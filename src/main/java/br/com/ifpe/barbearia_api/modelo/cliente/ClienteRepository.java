package br.com.ifpe.barbearia_api.modelo.cliente;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> { //informando que estou herdando JPA para a tabela cliente e o tipo da chave primária
    boolean existsByNome(String nome);
    Cliente findByUsuarioUsername(String username);
}

