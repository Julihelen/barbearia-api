package br.com.ifpe.barbearia_api.api.cliente;

import java.time.LocalDate;
import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ClienteRequest {

    private String nome;
    
    private LocalDate dataNascimento;
    
    private String cpf;
    
    private String endereco;
   
    private String foneCelular;
    
    private String email;
   
    private String senha;

   public Cliente build() {

       return Cliente.builder()
           .nome(nome)
           .dataNascimento(dataNascimento)
           .cpf(cpf)
           .endereco(endereco)
           .foneCelular(foneCelular)
           .email(email)
           .senha(senha)
           .build();
   }

}
