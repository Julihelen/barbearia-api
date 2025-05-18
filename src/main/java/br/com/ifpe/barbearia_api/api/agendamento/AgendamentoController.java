package br.com.ifpe.barbearia_api.api.agendamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
