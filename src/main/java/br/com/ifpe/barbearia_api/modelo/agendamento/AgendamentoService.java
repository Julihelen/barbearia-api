package br.com.ifpe.barbearia_api.modelo.agendamento;

import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import br.com.ifpe.barbearia_api.modelo.barbeiro.DisponibilidadeRepository;

@Service
public class AgendamentoService {
   private final DisponibilidadeRepository disponibilidadeRepo = null;
   private final AgendamentoRepository agendamentoRepo = null;
   private static final int DURACAO_SERVICO_MINUTOS = 40;
   @Autowired
   private AgendamentoRepository repository;

   @Transactional
   public Agendamento save(Agendamento agendamento) {

    agendamento.setHabilitado(Boolean.TRUE);
       return repository.save(agendamento);

       
   }
  
    public List<LocalTime> getHorariosDisponiveis(Long barbeiroId, LocalDate data) {
        List<Disponibilidade> disponibilidades = disponibilidadeRepo.findByBarbeiroIdAndDiaSemana(barbeiroId, data.getDayOfWeek());
        if (disponibilidades.isEmpty()) {
            return new ArrayList<>();
        }

        LocalDateTime inicioDoDia = data.atStartOfDay();
        LocalDateTime fimDoDia = data.atTime(LocalTime.MAX);
        List<Agendamento> agendamentosDoDia = agendamentoRepo.findAgendamentosNoDia(barbeiroId, inicioDoDia, fimDoDia);
        List<LocalTime> horariosOcupados = agendamentosDoDia.stream()
                .map(agendamento -> agendamento.getDataHoraInicio().toLocalTime())
                .collect(Collectors.toList());

        List<LocalTime> todosOsSlots = new ArrayList<>();
        for (Disponibilidade disp : disponibilidades) {
            LocalTime horarioAtual = disp.getHoraInicio();
            LocalTime horaFim = disp.getHoraFim();
            while (horarioAtual.isBefore(horaFim)) {
                if (!horariosOcupados.contains(horarioAtual)) {
                    todosOsSlots.add(horarioAtual);
                }
                horarioAtual = horarioAtual.plusMinutes(DURACAO_SERVICO_MINUTOS);
            }
        }
        return todosOsSlots;
    }
}


