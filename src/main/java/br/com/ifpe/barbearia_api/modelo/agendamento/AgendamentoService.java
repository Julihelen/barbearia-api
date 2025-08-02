package br.com.ifpe.barbearia_api.modelo.agendamento;

import jakarta.transaction.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroRepository;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import br.com.ifpe.barbearia_api.modelo.barbeiro.DisponibilidadeRepository;

@Service
public class AgendamentoService {
    @Autowired
    private BarbeiroRepository barbeiroRepository;
    @Autowired
    private DisponibilidadeRepository disponibilidadeRepo;

    @Autowired
    private AgendamentoRepository agendamentoRepo;
   private static final int DURACAO_SERVICO_MINUTOS = 40;
   @Autowired
   private AgendamentoRepository repository;

   @Transactional
   public Agendamento save(Agendamento agendamento) {

    agendamento.setHabilitado(Boolean.TRUE);
       return repository.save(agendamento);

       
   }
   @Autowired
    private AgendamentoRepository agendamentoRepository;

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }
  
    // public List<LocalTime> getHorariosDisponiveis(Long barbeiroId, LocalDate data) {
    //     // 1️⃣ Buscar o barbeiro para pegar os horários de atendimento
    //     Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
    //             .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

    //     LocalTime inicio = barbeiro.getAtendimentoInicio();
    //     LocalTime fim = barbeiro.getAtendimentoFim();

    //     // 2️⃣ Buscar agendamentos do dia inteiro
    //     LocalDateTime inicioDoDia = data.atStartOfDay();
    //     LocalDateTime fimDoDia = data.atTime(LocalTime.MAX);

    //     List<Agendamento> agendamentosDoDia =
    //             agendamentoRepo.findAgendamentosNoDia(barbeiroId, inicioDoDia, fimDoDia);

    //     // 3️⃣ Extrair horários ocupados
    //     List<LocalTime> horariosOcupados = agendamentosDoDia.stream()
    //             .map(a -> a.getDataHoraInicio().toLocalTime())
    //             .collect(Collectors.toList());

    //     // 4️⃣ Gerar slots de 40min do atendimentoInicio até atendimentoFim
    //     List<LocalTime> horariosDisponiveis = new ArrayList<>();
    //     LocalTime horarioAtual = inicio;

    //     while (!horarioAtual.isAfter(fim.minusMinutes(DURACAO_SERVICO_MINUTOS))) {
    //         if (!horariosOcupados.contains(horarioAtual)) {
    //             horariosDisponiveis.add(horarioAtual);
    //         }
    //         horarioAtual = horarioAtual.plusMinutes(DURACAO_SERVICO_MINUTOS);
    //     }

    //     return horariosDisponiveis;
    // }
   public List<LocalTime> getHorariosDisponiveis(Long barbeiroId, LocalDate data) {
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
            .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        LocalTime atendimentoInicio = barbeiro.getAtendimentoInicio();
        LocalTime atendimentoFim = barbeiro.getAtendimentoFim();

        System.out.println("Atendimento início: " + barbeiro.getAtendimentoInicio());
        System.out.println("Atendimento fim: " + barbeiro.getAtendimentoFim());
        LocalDateTime inicioDoDia = data.atTime(atendimentoInicio);
        LocalDateTime fimDoDia = data.atTime(atendimentoFim);

        List<Agendamento> agendamentosDoDia = agendamentoRepository.findAgendamentosNoDia(barbeiroId, inicioDoDia, fimDoDia);

        List<LocalTime> horariosOcupados = agendamentosDoDia.stream()
            .map(a -> a.getDataHoraInicio().toLocalTime())
            .collect(Collectors.toList());

        List<LocalTime> horariosDisponiveis = new ArrayList<>();
        LocalTime horarioAtual = atendimentoInicio;

        while (!horarioAtual.isAfter(atendimentoFim.minusMinutes(DURACAO_SERVICO_MINUTOS))) {
            if (!horariosOcupados.contains(horarioAtual)) {
                horariosDisponiveis.add(horarioAtual);
            }
            horarioAtual = horarioAtual.plusMinutes(DURACAO_SERVICO_MINUTOS);
        }

        System.out.println("Horários ocupados: " + horariosOcupados);
        System.out.println("Horários disponíveis: " + horariosDisponiveis);

        return horariosDisponiveis;
    }





}


