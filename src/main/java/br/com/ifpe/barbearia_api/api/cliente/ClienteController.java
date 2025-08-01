package br.com.ifpe.barbearia_api.api.cliente;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
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
import org.springframework.web.bind.annotation.RestController;

import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import br.com.ifpe.barbearia_api.modelo.cliente.ClienteService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/cliente")
@Tag(
    name = "API Cliente",
    description = "API responsável pelos serviços de cliente no sistema"
)
public class ClienteController {

    @Autowired
    private ClienteService clienteService;
    @Operation(
        summary = "Serviço responsável por salvar um cliente no sistema.",
        description = "Exemplo de descrição de um endpoint responsável por inserir um cliente no sistema."
    )

//    @PostMapping
//    public ResponseEntity<Cliente> save(@RequestBody ClienteRequest request) {
//        Cliente cliente = clienteService.save(request.build());
//        return new ResponseEntity<Cliente>(cliente, HttpStatus.CREATED);
//    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Cliente> save(@RequestBody ClienteRequest cliente) {
        Cliente clienteSalvo = clienteService.save(cliente.build());
        return new ResponseEntity<>(clienteSalvo, HttpStatus.CREATED);
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
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> atualizarCliente(
            @PathVariable Long id,
            @RequestBody Cliente clienteAtualizado) {

        try {
            Cliente cliente = clienteService.atualizar(id, clienteAtualizado);
            return ResponseEntity.ok(cliente);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
