package br.com.ifpe.barbearia_api.api.agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import br.com.ifpe.barbearia_api.modelo.agendamento.Agendamento;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AgendamentoRequest {

    private Cliente cliente;
    
    private LocalDate dataAtendimento;
    
    private Servico servico;
    
    private LocalTime horario;
   
    private Barbeiro barbeiro;
    
    private String observacoes;
   

   public Agendamento build() {

       return Agendamento.builder()
           .cliente(cliente)
           .dataAtendimento(dataAtendimento)
           .servico(servico)
           .horario(horario)
           .barbeiro(barbeiro)
           .observacoes(observacoes)
           .build();
   }

}
