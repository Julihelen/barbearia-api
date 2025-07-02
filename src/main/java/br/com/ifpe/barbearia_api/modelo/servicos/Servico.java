package br.com.ifpe.barbearia_api.modelo.servicos;


import org.hibernate.annotations.SQLRestriction;
import br.com.ifpe.barbearia_api.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Servico")
@SQLRestriction("habilitado = true") // acrescenta where em todas as consultas

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Servico extends EntidadeAuditavel  {
    @Column //A tabela que vai criar "cliente" vai criar uma coluna após criar um atributo e adicionar na respectiva coluna
    private String titulo;
    @Column
    private String descricao;
    @Column
    private double preco;
    @Column
    private Integer duracaoPadrao;
    

}
