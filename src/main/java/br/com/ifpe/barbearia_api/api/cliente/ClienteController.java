package br.com.ifpe.barbearia_api.api.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import br.com.ifpe.barbearia_api.modelo.cliente.ClienteService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

   @Autowired
   private ClienteService clienteService;

   @PostMapping
   public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) {
       Cliente cliente = clienteService.save(request.build());
       return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
   }

   @GetMapping
    public List<Cliente> listarTodos() {
        return clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Cliente buscarPorId(@PathVariable Long id) {
        return clienteService.obterPorID(id);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable Long id) {
        clienteService.remover(id);
    }
}
