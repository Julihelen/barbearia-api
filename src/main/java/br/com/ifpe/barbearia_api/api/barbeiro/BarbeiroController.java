package br.com.ifpe.barbearia_api.api.barbeiro;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroService;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/api/barbeiros")
@RequiredArgsConstructor
@CrossOrigin
public class BarbeiroController {

    private final BarbeiroService barbeiroService;

    // Endpoint para criar o barbeiro com dados simples
    @PostMapping
    public ResponseEntity<Barbeiro> criarBarbeiro(@RequestBody Barbeiro barbeiro) {
        Barbeiro barbeiroSalvo = barbeiroService.salvar(barbeiro);
        return new ResponseEntity<>(barbeiroSalvo, HttpStatus.CREATED);
    }
    
    // Endpoint para ASSOCIAR serviços a um barbeiro já existente
    @PostMapping("/{barbeiroId}/servicos")
    public ResponseEntity<Barbeiro> associarServicos(
            @PathVariable Long barbeiroId, 
            @RequestBody Map<String, Set<Long>> requestBody) {
        
        Set<Long> servicoIds = requestBody.get("servicoIds");
        Barbeiro barbeiroAtualizado = barbeiroService.associarServicos(barbeiroId, servicoIds);
        return ResponseEntity.ok(barbeiroAtualizado);
    }

    // Endpoint para listar todos (agora retorna a entidade diretamente)
    @GetMapping
    public List<Barbeiro> listarTodos() {
        return barbeiroService.listarTodos();
    }




    

}