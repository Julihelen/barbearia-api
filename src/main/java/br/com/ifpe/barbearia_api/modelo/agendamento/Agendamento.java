package br.com.ifpe.barbearia_api.modelo.agendamento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
    @Column
    private LocalDate dataAtendimento;
    @ManyToOne
    @JoinColumn(name = "servico_id") // <-- Adicionar/Verificar este
    private Servico servico;
    @Column
    private LocalTime horario;
    @ManyToOne
    @JoinColumn(name = "barbeiro_id")
    private Barbeiro barbeiro;
    @Column
    private String observacoes;
    private LocalDateTime dataHoraInicio;
    private LocalDateTime dataHoraFim;
    private String status;
}

