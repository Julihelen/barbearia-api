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

@RestController
@RequestMapping("/api/agendamento")
@CrossOrigin
public class AgendamentoController {

   @Autowired
   private AgendamentoService AgendamentoService;

   @PostMapping
   public ResponseEntity<Agendamento> save(@RequestBody AgendamentoRequest request) {

    Agendamento agendamento = AgendamentoService.save(request.build());
       return new ResponseEntity<Agendamento>(agendamento, HttpStatus.CREATED);
   }

   @GetMapping("api/disponibilidade/{Id}")
    public List<LocalTime> getHorarios(
            @PathVariable Long barbeiroId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
        return AgendamentoService.getHorariosDisponiveis(barbeiroId, data);
    }
}
