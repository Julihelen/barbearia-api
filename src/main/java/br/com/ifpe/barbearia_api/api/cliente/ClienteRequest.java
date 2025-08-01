package br.com.ifpe.barbearia_api.api.cliente;

import java.time.LocalDate;
import java.util.Arrays;

import br.com.ifpe.barbearia_api.modelo.acesso.Perfil;
import br.com.ifpe.barbearia_api.modelo.acesso.Usuario;
import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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
    
    @NotBlank(message = "O e-mail é de preenchimento obrigatório")
    @Email
    private String email;

    @NotBlank(message = "A senha é de preenchimento obrigatório")
    private String password;

    public Usuario buildUsuario() {
       return Usuario.builder()
           .username(email)
           .password(password)
           .roles(Arrays.asList(new Perfil(Perfil.ROLE_FUNCIONARIO_ADMIN)))
           .build();
   }

    public Cliente build() {
        return Cliente.builder()
            .usuario(buildUsuario())
            .nome(nome)
            .dataNascimento(dataNascimento)
            .cpf(cpf)
            .endereco(endereco)
            .foneCelular(foneCelular)
            .build();
    }

}
