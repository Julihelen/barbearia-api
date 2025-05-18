package br.com.ifpe.barbearia_api.modelo.agendamento;

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
@Table(name = "Agenda")
@SQLRestriction("habilitado = true") // acrescenta where em todas as consultas
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Agendamento extends EntidadeAuditavel  {

    @Column //A tabela que vai criar "Agenda" vai criar uma coluna após criar um atributo e adicionar na respectiva coluna
    private String nome;
    @Column
    private LocalDate dataAtendimento;
    @Column
    private String servico;
    @Column
    private LocalTime horario;
    @Column
    private String barbeiro;
    @Column
    private String observacoes;

}
