package br.com.ifpe.barbearia_api.api.agendamento;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.barbearia_api.modelo.agendamento.Agendamento;
import br.com.ifpe.barbearia_api.modelo.agendamento.AgendamentoService;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;

@RestController
@RequestMapping("/api/agendamento")
@CrossOrigin
public class AgendamentoController {

   @Autowired
   private AgendamentoService agendamentoService;
//    @Autowired
//    private Object clienteService;
//    @Autowired
//    private Object servicoService;
//    @Autowired
//    private Object barbeiroService;


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

   // Para o POST de agendamento, pode comentar o cliente e focar só no básico
   @PostMapping
   public ResponseEntity<?> save(@RequestBody AgendamentoRequest request) {
    //    List<Cliente> cliente = clienteService.obterPorID(request.getClienteId()); // comentado por enquanto
       

       // aqui, só use barbeiro e servico se quiser testar mesmo
    //    List<Barbeiro> barbeiro = barbeiroService.obterPorID(request.getBarbeiroId());
    //    List<Servico> servico = servicoService.obterPorID(request.getServicoId());

    //    Agendamento agendamento = agendamentoService.save(request.build(cliente, servico, barbeiro));
    //    return new ResponseEntity<>(agendamento, HttpStatus.CREATED);

       return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body("Aguarde, implementação do agendamento comentada por enquanto.");
   }
}
