package br.com.ifpe.barbearia_api.modelo.cliente;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ifpe.barbearia_api.modelo.acesso.Perfil;
import br.com.ifpe.barbearia_api.modelo.acesso.PerfilRepository;
import br.com.ifpe.barbearia_api.modelo.acesso.UsuarioService;

@Service
public class ClienteService {
    @Autowired
   private UsuarioService usuarioService;

   @Autowired
   private PerfilRepository perfilUsuarioRepository;


   @Autowired
   private ClienteRepository repository;
   private Cliente clienteAtualizado;

   @Transactional
   public Cliente save(Cliente cliente) {
        usuarioService.save(cliente.getUsuario());
        for (Perfil perfil : cliente.getUsuario().getRoles()) {
            perfil.setHabilitado(Boolean.TRUE);
            perfilUsuarioRepository.save(perfil);
        }
        cliente.setHabilitado(Boolean.TRUE);
        Cliente clienteSalvo = repository.save(cliente);
        return clienteSalvo;

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
    public Cliente atualizar(Long id, Cliente clienteAtualizado){
         Cliente clienteExistente = repository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Cliente não encontrado com id: " + id));

        // Atualizar os campos que quiser permitir alteração
        clienteExistente.setNome(clienteAtualizado.getNome());
        clienteExistente.setFoneCelular(clienteAtualizado.getFoneCelular());
        clienteExistente.setCpf(clienteAtualizado.getCpf());
        // Atualize outros campos se necessário

        // Salvar cliente atualizado
        return repository.save(clienteExistente);
    }

}
