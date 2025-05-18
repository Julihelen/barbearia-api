package br.com.ifpe.barbearia_api.api.agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import br.com.ifpe.barbearia_api.modelo.agendamento.Agendamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequest {

    private String nome;
    
    private LocalDate dataAtendimento;
    
    private String servico;
    
    private LocalTime horario;
   
    private String barbeiro;
    
    private String observacoes;
   

   public Agendamento build() {

       return Agendamento.builder()
           .nome(nome)
           .dataAtendimento(dataAtendimento)
           .servico(servico)
           .horario(horario)
           .barbeiro(barbeiro)
           .observacoes(observacoes)
           .build();
   }

}
