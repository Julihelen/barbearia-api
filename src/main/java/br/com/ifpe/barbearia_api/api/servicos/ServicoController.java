package br.com.ifpe.barbearia_api.api.servicos;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/servicos")
@CrossOrigin
public class ServicoController {

    private final ServicoService servicoService;

    // Injeção via construtor (boa prática)
    public ServicoController(ServicoService servicoService) {
        this.servicoService = servicoService;
    }

    // Criar novo serviço
    @PostMapping
    public ResponseEntity<Servico> save(@RequestBody @Valid ServicoRequest request) {
        Servico servico = request.build();
        Servico servicoSalvo = servicoService.save(servico);
        return new ResponseEntity<>(servicoSalvo, HttpStatus.CREATED);
    }

    // Listar todos os serviços
    @GetMapping
    public ResponseEntity<List<Servico>> listarTodos() {
        List<Servico> servicos = servicoService.listarTodos();
        return ResponseEntity.ok(servicos);
    }

    // Buscar serviço por ID
    @GetMapping("/{id}")
    public ResponseEntity<Servico> obterPorID(@PathVariable Long id) {
        Servico servico = servicoService.obterPorID(id);
        if (servico == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(servico);
    }

    // Atualizar serviço
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody @Valid ServicoRequest request) {
        Servico servicoAlterado = request.build();
        servicoService.update(id, servicoAlterado);
        return ResponseEntity.ok().build();
    }

    // Deletar (desabilitar) serviço
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servicoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
