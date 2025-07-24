package br.com.ifpe.barbearia_api.modelo.cliente;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService {

   @Autowired
   private ClienteRepository repository;

   @Transactional
   public Cliente save(Cliente cliente) {

       cliente.setHabilitado(Boolean.TRUE);
       return repository.save(cliente);

   }
    public List<Cliente> listarTodos() {
        return repository.findAll();
    }


   public Cliente obterPorID(Long id) {
        return repository.findById(id)
        .orElseThrow(() -> new RuntimeException("Cliente não encontrado com ID: " + id));
    }

     public void remover(Long id) {
        repository.deleteById(id);
    }

}
