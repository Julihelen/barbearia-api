package br.com.ifpe.barbearia_api.api.barbeiro;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarbeiroRequest {

    // Dados Pessoais
    private String nome;
    private String foneCelular;
    private String email;
    private LocalDate dataNascimento;
    private String cpf;
    private String endereco;
    private String senha;

    // Dados de Disponibilidade (Exemplo para um único turno de trabalho)
    // Para múltiplos dias, o ideal seria uma lista de objetos de disponibilidade
    private Set<DayOfWeek> diasDeTrabalho; // Ex: [MONDAY, TUESDAY, WEDNESDAY]
    private LocalTime atendimentoInicio;
    private LocalTime atendimentoFim;
    
    // A forma correta de receber os "skills"
    private Set<Long> servicoIds;
    
    // REMOVEMOS O MÉTODO build() DAQUI!

}
