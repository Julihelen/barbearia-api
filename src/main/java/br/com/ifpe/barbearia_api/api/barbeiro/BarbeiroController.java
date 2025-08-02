package br.com.ifpe.barbearia_api.api.barbeiro;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.persistence.EntityNotFoundException;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroRepository;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroRequest;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroService;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/barbeiros")
@CrossOrigin
@Tag(
    name = "API Barbeiro",
    description = "API responsável pelo cadastro de barbeiro no sistema"
)
public class BarbeiroController {
    @Autowired
    private BarbeiroService barbeiroService;
    @Autowired
    private ServicoRepository servicoRepository;
   
    @Operation(
       summary = "Serviço responsável por salvar um cliente no sistema.",
       description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )

    // Endpoint para criar o barbeiro com dados simples
   @PostMapping
    public ResponseEntity<Barbeiro> criarBarbeiro(@RequestBody BarbeiroRequest request) {
        // Converte o DTO em entidade
        Barbeiro barbeiro = request.buildBarbeiro();

        // Busca os serviços no banco com base nos IDs enviados
        Set<Servico> servicos = servicoRepository.findAllById(request.getServicoIds())
                                                .stream()
                                                .collect(Collectors.toSet());

        // Associa os serviços ao barbeiro
        barbeiro.setServicos(servicos);

        // Salva o barbeiro já com os serviços
        Barbeiro barbeiroSalvo = barbeiroService.salvar(barbeiro);
        return new ResponseEntity<>(barbeiroSalvo, HttpStatus.CREATED);
    }

    
    // Endpoint para ASSOCIAR serviços a um barbeiro já existente
    @PostMapping("/{barbeiroId}/servicos")
    public ResponseEntity<Barbeiro> associarServicos(
            @PathVariable Long barbeiroId,
            @RequestBody Map<String, Set<Long>> requestBody) {

        Set<Long> servicoIds = requestBody.get("servicoIds");
        Barbeiro barbeiroAtualizado = barbeiroService.associarServicos(barbeiroId, servicoIds);
        return ResponseEntity.ok(barbeiroAtualizado);
    }


    // Endpoint para listar todos (agora retorna a entidade diretamente)
    @GetMapping
    public List<Barbeiro> listarTodos() {
        return barbeiroService.listarTodos();
    }
    //lista por serviço disponivel do barbeiro
    @GetMapping("/por-servico/{idServico}")
    public ResponseEntity<List<Barbeiro>> buscarPorServico(@PathVariable Long idServico) {
        List<Barbeiro> barbeiros = barbeiroService.buscarPorIdServico(idServico);
        return ResponseEntity.ok(barbeiros);
    }

    // @GetMapping("/filtro-por-servico-com-disponibilidade")
    // public List<BarbeiroComDisponibilidadeDTO> listarPorServicoComDisponibilidade(@RequestParam String servico) {
    //     return barbeiroService.buscarPorServicoComDisponibilidade(servico);
    // }

    @GetMapping("/{barbeiroId}/horarios-disponiveis")
    public ResponseEntity<List<LocalTime>> listarHorariosDisponiveis(
        @PathVariable Long barbeiroId,
        @RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {

        List<LocalTime> horarios = barbeiroService.obterHorariosDisponiveis(barbeiroId, data);
        return ResponseEntity.ok(horarios);
    }


    // Endpoint para atualizar barbeiro por ID
    @PutMapping("/{id}")
    public ResponseEntity<Barbeiro> atualizarBarbeiro(@PathVariable Long id, @RequestBody Barbeiro barbeiroAtualizado) {
        try {
            Barbeiro barbeiroSalvo = barbeiroService.atualizar(id, barbeiroAtualizado);
            return ResponseEntity.ok(barbeiroSalvo);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbeiro não encontrado", e);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        barbeiroService.deletar(id);
        return ResponseEntity.noContent().build();
    }
   
    



    

}