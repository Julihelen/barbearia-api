package br.com.ifpe.barbearia_api.modelo.agendamento;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendamentoService {

   @Autowired
   private AgendamentoRepository repository;

   @Transactional
   public Agendamento save(Agendamento agendamento) {

    agendamento.setHabilitado(Boolean.TRUE);
       return repository.save(agendamento);

       
   }

}
