package br.com.ifpe.barbearia_api.modelo.servicos;

import org.hibernate.annotations.SQLRestriction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import br.com.ifpe.barbearia_api.util.entity.EntidadeAuditavel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "servico")
// @SQLRestriction("habilitado = true") // acrescenta where em todas as consultas
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id") // ✅ Igual ao Barbeiro
public class Servico extends EntidadeAuditavel{


    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private Double preco;

    @Column
    private Integer duracaoPadrao;
    @Column(nullable = false)
    @Builder.Default
    private Boolean habilitado = true;
}
