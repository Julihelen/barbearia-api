package br.com.ifpe.barbearia_api.api.barbeiro;

import java.time.LocalDate;
import java.time.LocalTime;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarbeiroRequest {

    private String nome;
    
    private String foneCelular;
   
    private String email;
   
    private LocalDate dataNascimento;
  
    private String cpf;

    private String endereco;

    private LocalTime atendimentoInicio;

    private LocalTime atendimentoFim;
  
    private String skills;
    
    private String senha;
   

   public Barbeiro build() {

       return Barbeiro.builder()
           .nome(nome)
           .foneCelular(foneCelular)
           .email(email)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .endereco(endereco)
           .atendimentoInicio(atendimentoInicio)
           .atendimentoFim(atendimentoFim)
           .skills(skills)
           .senha(senha)
           .build();
   }

}
