package br.com.ifpe.barbearia_api.modelo.cliente;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import java.time.LocalDate;
import java.util.List;

import org.hibernate.annotations.SQLRestriction;

import br.com.ifpe.barbearia_api.modelo.acesso.Usuario;
// import br.com.ifpe.barbearia_api.modelo.acesso.Usuario;
import br.com.ifpe.barbearia_api.modelo.agendamento.Agendamento;
import br.com.ifpe.barbearia_api.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Cliente")
// @SQLRestriction("habilitado = true") // acrescenta where em todas as consultas
// @Builderpublic ClienteRequestBuilder password(
//             String password) {
//         // TODO Auto-generated method stub
//         throw new UnsupportedOperationException("Unimplemented method 'password'");
//     }
@Getter
@Setter
@Builder 
@AllArgsConstructor
@NoArgsConstructor
public class Cliente extends EntidadeAuditavel  {
    @OneToOne
    @JoinColumn(nullable = false)
    private Usuario usuario;


    @Column //A tabela que vai criar "cliente" vai criar uma coluna após criar um atributo e adicionar na respectiva coluna
    private String nome;
    @Column
    private LocalDate dataNascimento;
    @Column
    private String cpf;
    @Column
    private String endereco;
    @Column
    private String foneCelular;
    @Column
    private String email;
    @Column
    private String password;
    
    @OneToMany(mappedBy = "cliente")
    @Fetch(FetchMode.SUBSELECT)
    private List<Agendamento> agendamentos;

}
