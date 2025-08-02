package br.com.ifpe.barbearia_api.modelo.barbeiro;

import java.sql.Array;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarbeiroRequest {

    @NotBlank
    private String nome;

    @NotBlank
    private String foneCelular;

    @Email
    @NotBlank
    private String email;

    @NotNull
    private LocalDate dataNascimento;

    @NotBlank
    private String cpf;

    @NotBlank
    private String endereco;

    @NotNull
    private LocalTime atendimentoInicio;

    @NotNull
    private LocalTime atendimentoFim;

    @NotBlank
    private String senha;

    public Barbeiro buildBarbeiro() {
        return Barbeiro.builder()
            .nome(nome)
            .foneCelular(foneCelular)
            .dataNascimento(dataNascimento)
            .cpf(cpf)
            .endereco(endereco)
            .atendimentoInicio(atendimentoInicio)
            .atendimentoFim(atendimentoFim)
            .senha(senha)
            .build();
    }
}
