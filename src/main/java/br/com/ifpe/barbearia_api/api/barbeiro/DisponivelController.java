package br.com.ifpe.barbearia_api.api.barbeiro;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import br.com.ifpe.barbearia_api.modelo.barbeiro.DisponibilidadeRepository;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/disponibilidade")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class DisponivelController {

    private final DisponibilidadeRepository disponibilidadeRepository;

    @GetMapping("/{barbeiroId}")
    public List<String> buscarHorariosDisponiveis(
            @PathVariable @NotNull Long barbeiroId,
            @RequestParam @NotNull DayOfWeek diaSemana) {

        List<Disponibilidade> disponiveis = disponibilidadeRepository.findByBarbeiroIdAndDiaSemana(barbeiroId, diaSemana);

        List<String> horarios = new ArrayList<>();

        for (Disponibilidade d : disponiveis) {
            LocalTime horaAtual = d.getHoraInicio();
            while (!horaAtual.isAfter(d.getHoraFim().minusMinutes(40))) {
                horarios.add(horaAtual.toString());
                horaAtual = horaAtual.plusMinutes(40);
            }
        }

        return horarios;
    }
    
}