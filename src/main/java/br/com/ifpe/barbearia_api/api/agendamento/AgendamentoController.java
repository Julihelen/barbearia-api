package br.com.ifpe.barbearia_api.api.agendamento;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.barbearia_api.modelo.agendamento.Agendamento;
import br.com.ifpe.barbearia_api.modelo.agendamento.AgendamentoRepository;
import br.com.ifpe.barbearia_api.modelo.agendamento.AgendamentoService;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroRepository;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroService;
import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import br.com.ifpe.barbearia_api.modelo.cliente.ClienteRepository;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoRepository;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoService;

@RestController
@RequestMapping("/api/agendamento")
@CrossOrigin
@Tag(
    name = "API Agendamento",
    description = "API responsável pelos agendamentos no sistema"
)
public class AgendamentoController {
    @Autowired
    private ServicoService servicoService;
    @Autowired
    private BarbeiroService barbeiroService;
    @Autowired
    private AgendamentoService agendamentoService;
     @Autowired
    private AgendamentoRepository agendamentoRepository;
     @Autowired
    private ClienteRepository clienteRepository;
     @Autowired
    private BarbeiroRepository barbeiroRepository;
    @Autowired
    private ServicoRepository servicoRepository;
    @Operation(
        summary = "Serviço responsável por salvar um cliente no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )
//    @Autowired
//    private Object clienteService;
    


    @GetMapping
    public List<Agendamento> listarTodos() {
        return agendamentoService.listarTodos();
    }

   @GetMapping("/disponibilidade/{barbeiroId}")
   public ResponseEntity<List<LocalTime>> getHorarios(
           @PathVariable Long barbeiroId,
           @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
       List<LocalTime> horarios = agendamentoService.getHorariosDisponiveis(barbeiroId, data);
       return ResponseEntity.ok(horarios);
   }

//    @PostMapping(consumes = {"application/json", "application/json;charset=UTF-8"}, produces = "application/json")
//     public ResponseEntity<Agendamento> save(@RequestBody Agendamento agendamento) {
//         Agendamento agendamentoSalvo = agendamentoService.save(agendamento);
//         return new ResponseEntity<>(agendamentoSalvo, HttpStatus.CREATED);
//     }
    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Agendamento> save(@RequestBody AgendamentoRequest request) {
        System.out.println("📥 JSON recebido no backend: " + request);

        Servico servico = servicoService.obterPorID(request.getServicoId());
        Barbeiro barbeiro = barbeiroService.obterPorID(request.getBarbeiroId());

        // Se cliente não for enviado, pode ser null aqui
        Cliente cliente = null; // Ou algum cliente padrão se quiser

        Agendamento agendamento = request.build(cliente, servico, barbeiro);

        Agendamento salvo = agendamentoService.save(agendamento);

        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

   @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizarAgendamento(
        @PathVariable Long id,
        @RequestBody AgendamentoRequest request) {

        return agendamentoRepository.findById(id)
            .map(agendamentoExistente -> {
                // Atualiza só os campos permitidos
                agendamentoExistente.setDataAtendimento(request.getDataAtendimento());
                agendamentoExistente.setHorario(request.getHorario());

                // Opcional: atualizar observações, se quiser
                agendamentoExistente.setObservacoes(request.getObservacoes());

                agendamentoRepository.save(agendamentoExistente);
                return ResponseEntity.ok(agendamentoExistente);
            })
            .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarAgendamento(@PathVariable Long id) {
        if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
