package br.com.ifpe.barbearia_api.modelo.barbeiro;

import java.time.LocalDate;
import java.time.LocalTime;

import org.hibernate.annotations.SQLRestriction;
import br.com.ifpe.barbearia_api.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
    @Column
    private String skills;
    @Column
    private String senha;


}
