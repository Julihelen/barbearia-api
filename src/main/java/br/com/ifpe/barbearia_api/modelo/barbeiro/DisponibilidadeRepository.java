package br.com.ifpe.barbearia_api.modelo.barbeiro;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.DayOfWeek; // Import que faltava
import java.util.List;

public interface DisponibilidadeRepository extends JpaRepository<Disponibilidade, Long> {

    /**
     * Busca as regras de disponibilidade de um barbeiro para um dia específico da semana.
     * O Spring Data JPA cria a query automaticamente pelo nome do método.
     */
    List<Disponibilidade> findByBarbeiroIdAndDiaSemana(Long barbeiroId, DayOfWeek diaSemana);
}