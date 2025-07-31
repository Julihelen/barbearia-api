package br.com.ifpe.barbearia_api.modelo.barbeiro;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {
    boolean existsByNome(String nome);
    // List<Barbeiro> findByServicosTitulo(String titulo);

    @Query("SELECT b FROM Barbeiro b JOIN b.servicos s WHERE s.id = :idServico")
    List<Barbeiro> buscarPorIdServico(@Param("idServico") Long idServico);
    //  MÉTODO ABAIXO FOI REMOVIDO DESTE ARQUIVO
    // List<Disponibilidade> findByBarbeiroIdAndDiaSemana(Long barbeiroId, DayOfWeek diaSemana);

    
}
