package br.com.ifpe.barbearia_api.api.servicos;

import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServicoRequest {

    @NotBlank(message = "O título é obrigatório")
    private String titulo;

    private String descricao;

    @NotNull(message = "O preço é obrigatório") 
    private Double preco;

    private Integer duracaoPadrao;

    public Servico build() {
        return Servico.builder()
            .titulo(titulo)
            .descricao(descricao)
            .preco(preco)
            .duracaoPadrao(duracaoPadrao)
            .build();
    }
}
