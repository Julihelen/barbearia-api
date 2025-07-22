package br.com.ifpe.barbearia_api.api.barbeiro;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import java.util.List;

public class BarbeiroComDisponibilidadeDTO {
    private Long id;
    private String nome;
    private List<Disponibilidade> disponibilidade;

    public BarbeiroComDisponibilidadeDTO(Long id, String nome, List<Disponibilidade> disponibilidade) {
        this.id = id;
        this.nome = nome;
        this.disponibilidade = disponibilidade;
    }

    // Getters e setters (ou use Lombok @Getter @Setter se preferir)
}
