package br.com.ifpe.barbearia_api.api.config;

import br.com.ifpe.barbearia_api.modelo.agendamento.Agendamento;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.modelo.agendamento.AgendamentoRepository;
import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroRepository;
import br.com.ifpe.barbearia_api.modelo.barbeiro.DisponibilidadeRepository;
import br.com.ifpe.barbearia_api.modelo.cliente.ClienteRepository;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final ClienteRepository clienteRepository;
    private final AgendamentoRepository agendamentoRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println("--- Carregando dados iniciais para teste... ---");

        // 1. Criar Serviços
        Servico servicoCorte = new Servico();
        servicoCorte.setTitulo("Corte de Cabelo");
        servicoRepository.save(servicoCorte);

        Servico servicoBarba = new Servico();
        servicoBarba.setTitulo("Barba Terapia");
        servicoRepository.save(servicoBarba);

        // 2. Criar Barbeiros e associar serviços
        Barbeiro barbeiroJoao = new Barbeiro();
        barbeiroJoao.setNome("João da Silva");
        barbeiroJoao.setServicos(Set.of(servicoCorte, servicoBarba)); // João faz tudo
        barbeiroRepository.save(barbeiroJoao);

        Barbeiro barbeiroPedro = new Barbeiro();
        barbeiroPedro.setNome("Pedro Souza");
        barbeiroPedro.setServicos(Set.of(servicoCorte)); // Pedro só corta cabelo
        barbeiroRepository.save(barbeiroPedro);

        // 3. Criar Disponibilidade para os Barbeiros
        // João trabalha na Segunda de manhã (hoje é Segunda, 30/06/2025)
        Disponibilidade dispJoao = new Disponibilidade();
        dispJoao.setBarbeiro(barbeiroJoao);
        dispJoao.setDiaSemana(DayOfWeek.MONDAY);
        dispJoao.setHoraInicio(LocalTime.of(9, 0));
        dispJoao.setHoraFim(LocalTime.of(12, 0));
        disponibilidadeRepository.save(dispJoao);

        // Pedro trabalha na Segunda o dia todo
        Disponibilidade dispPedro = new Disponibilidade();
        dispPedro.setBarbeiro(barbeiroPedro);
        dispPedro.setDiaSemana(DayOfWeek.MONDAY);
        dispPedro.setHoraInicio(LocalTime.of(10, 0));
        dispPedro.setHoraFim(LocalTime.of(17, 0));
        disponibilidadeRepository.save(dispPedro);

        // 4. Criar um Cliente
        Cliente clienteAna = new Cliente();
        clienteAna.setNome("Ana Lúcia");
        clienteRepository.save(clienteAna);

        // 5. Criar um AGENDAMENTO JÁ EXISTENTE para testar o filtro
        // Vamos marcar um horário com o João para vermos o horário sumir da lista
        LocalDate hoje = LocalDate.of(2025, 6, 30);
        Agendamento agendamentoExistente = Agendamento.builder()
                .barbeiro(barbeiroJoao)
                .cliente(clienteAna)
                .servico(servicoCorte)
                .dataHoraInicio(hoje.atTime(10, 20)) // Marcando às 10:20
                .dataHoraFim(hoje.atTime(11, 0)) // 40 min depois
                .status("Confirmado")
                .build();
        agendamentoRepository.save(agendamentoExistente);

        System.out.println("--- Dados carregados com sucesso! ---");
    }
}