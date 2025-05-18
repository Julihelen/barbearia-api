package br.com.ifpe.barbearia_api.modelo.barbeiro;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BarbeiroService {

   @Autowired
   private BarbeiroRepository repository;

   @Transactional
   public Barbeiro save(Barbeiro barbeiro) {

    barbeiro.setHabilitado(Boolean.TRUE);
       return repository.save(barbeiro);

       
   }

}
