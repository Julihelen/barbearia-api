package br.com.ifpe.barbearia_api.modelo.agendamento;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Long> { //informando que estou herdando JPA para a tabela agenda e o tipo da chave primária

  /**
     * Query para buscar agendamentos de um barbeiro em um período específico (um dia inteiro).
     */
    @Query("SELECT a FROM Agendamento a WHERE a.barbeiro.id = :barbeiroId AND a.dataHoraInicio >= :inicioDia AND a.dataHoraFim <= :fimDia")
    List<Agendamento> findAgendamentosNoDia(Long barbeiroId, LocalDateTime inicioDia, LocalDateTime fimDia);
}


