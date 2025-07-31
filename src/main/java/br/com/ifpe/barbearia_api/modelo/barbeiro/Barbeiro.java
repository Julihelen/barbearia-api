package br.com.ifpe.barbearia_api.modelo.barbeiro;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "barbeiro")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Barbeiro extends EntidadeAuditavel {

    @Column
    private String nome;
    @Column
    private String foneCelular;
    @Column
    private String email;
    @Column
    private LocalDate dataNascimento;
    @Column
    private String cpf;
    @Column
    private String endereco;
    @Column
    private LocalTime atendimentoInicio;
    @Column
    private LocalTime atendimentoFim;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "barbeiro_servico",
    joinColumns = @JoinColumn(name = "barbeiro_id"),
    inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    private Set<Servico> servicos = new HashSet<>();

    @Column
    private String senha;

    // public List<Long> getServicoIdsList() {
    //     if (servicoIds == null || servicoIds.trim().isEmpty()) {
    //         return new ArrayList<>();
    //     }
    //     return Arrays.stream(servicoIds.split(","))
    //             .map(String::trim)
    //             .map(Long::parseLong)
    //             .collect(Collectors.toList());
    // }

    // public void setServicoIdsList(List<Long> ids) {
    //     if (ids == null || ids.isEmpty()) {
    //         this.servicoIds = null;
    //     } else {
    //         this.servicoIds = ids.stream()
    //                 .map(String::valueOf)
    //                 .collect(Collectors.joining(","));
    //     }
    // }
}