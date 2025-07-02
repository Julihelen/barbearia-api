package br.com.ifpe.barbearia_api.modelo.barbeiro;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
@SQLRestriction("habilitado = true") // acrescenta where em todas as consultas
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Barbeiro extends EntidadeAuditavel  {

    @Column //A tabela que vai criar "Barbeiro" vai criar uma coluna após criar um atributo e adicionar na respectiva coluna
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
        name = "barbeiro_servicos",
        joinColumns = @JoinColumn(name = "barbeiro_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
    @JsonManagedReference // <-- RESOLVE O LOOP (Lado "normal" da relação)
    private Set<Servico> servicos;
    @Column
    private String senha;


}
