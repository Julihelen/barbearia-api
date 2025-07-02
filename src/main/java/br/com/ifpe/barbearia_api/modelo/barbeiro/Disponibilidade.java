package br.com.ifpe.barbearia_api.modelo.barbeiro;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.DayOfWeek;
import java.time.LocalTime;

import br.com.ifpe.barbearia_api.util.entity.EntidadeAuditavel;

@Entity
@Table(name = "disponibilidade")
@Getter
@Setter
@NoArgsConstructor
public class Disponibilidade extends EntidadeAuditavel{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "barbeiro_id", nullable = false)
    private Barbeiro barbeiro;

    @Enumerated(EnumType.ORDINAL)
    private DayOfWeek diaSemana;

    private LocalTime horaInicio;
    private LocalTime horaFim;
} 
