// package br.com.ifpe.barbearia_api.config;
// // package br.com.ifpe.barbearia_api.api.config;

// // import br.com.ifpe.barbearia_api.modelo.agendamento.Agendamento;
// // import br.com.ifpe.barbearia_api.modelo.barbeiro.Barbeiro;
// // import br.com.ifpe.barbearia_api.modelo.barbeiro.Disponibilidade;
// // import br.com.ifpe.barbearia_api.modelo.cliente.Cliente;
// // import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
// // import br.com.ifpe.barbearia_api.modelo.agendamento.AgendamentoRepository;
// // import br.com.ifpe.barbearia_api.modelo.barbeiro.BarbeiroRepository;
// // import br.com.ifpe.barbearia_api.modelo.barbeiro.DisponibilidadeRepository;
// // import br.com.ifpe.barbearia_api.modelo.cliente.ClienteRepository;
// // import br.com.ifpe.barbearia_api.modelo.servicos.ServicoRepository;
// // import lombok.RequiredArgsConstructor;
// // import org.springframework.boot.CommandLineRunner;
// // import org.springframework.stereotype.Component;
// // import org.springframework.core.env.Environment; // <-- Adicione este import

// // import java.time.DayOfWeek;
// // import java.time.LocalDate;
// // import java.time.LocalTime;
// // import java.util.Set;


// // // SCRIPT PARA POPULAR BANCO
// // @Component
// // @RequiredArgsConstructor
// // public class DataLoader implements CommandLineRunner {

// //     private final BarbeiroRepository barbeiroRepository;
// //     private final ServicoRepository servicoRepository;
// //     private final DisponibilidadeRepository disponibilidadeRepository;
// //     private final ClienteRepository clienteRepository;
// //     private final AgendamentoRepository agendamentoRepository;
// //     private final Environment env;
  

// //     @Override
// //     public void run(String... args) throws Exception {
// //         System.out.println("--- Verificando se o banco precisa ser populado... ---");

// //         String ddlAutoValue = env.getProperty("spring.jpa.hibernate.ddl-auto");
// //         System.out.println("===============================================================");
// //         System.out.println("VALOR REAL DE 'ddl-auto' QUE O SPRING ESTÁ USANDO: " + ddlAutoValue);
// //         System.out.println("===============================================================");

// //         boolean dadosJaExistem =
// //             servicoRepository.existsByTitulo("Corte de Cabelo") &&
// //             servicoRepository.existsByTitulo("Barba Terapia") &&
// //             barbeiroRepository.existsByNome("João da Silva") &&
// //             barbeiroRepository.existsByNome("Pedro Souza") &&
// //             clienteRepository.existsByNome("Ana Lúcia");

// //         if (dadosJaExistem) {
// //             System.out.println("--- Dados já existentes. Nenhuma inserção realizada. ---");
// //             return;
// //         }

// //         // 1. Criar Serviços
// //         Servico servicoCorte = new Servico();
// //         servicoCorte.setTitulo("Corte de Cabelo");
// //         servicoRepository.save(servicoCorte);

// //         Servico servicoBarba = new Servico();
// //         servicoBarba.setTitulo("Barba Terapia");
// //         servicoRepository.save(servicoBarba);

// //         // 2. Criar Barbeiros e associar serviços
// //         Barbeiro barbeiroJoao = new Barbeiro();
// //         barbeiroJoao.setNome("João da Silva");
// //         barbeiroJoao.setServicos(Set.of(servicoCorte, servicoBarba));
// //         barbeiroRepository.save(barbeiroJoao);

// //         Barbeiro barbeiroPedro = new Barbeiro();
// //         barbeiroPedro.setNome("Pedro Souza");
// //         barbeiroPedro.setServicos(Set.of(servicoCorte));
// //         barbeiroRepository.save(barbeiroPedro);

// //         // 3. Criar Disponibilidade
// //         Disponibilidade dispJoao = new Disponibilidade();
// //         dispJoao.setBarbeiro(barbeiroJoao);
// //         dispJoao.setDiaSemana(DayOfWeek.MONDAY);
// //         dispJoao.setHoraInicio(LocalTime.of(9, 0));
// //         dispJoao.setHoraFim(LocalTime.of(12, 0));
// //         disponibilidadeRepository.save(dispJoao);

// //         Disponibilidade dispPedro = new Disponibilidade();
// //         dispPedro.setBarbeiro(barbeiroPedro);
// //         dispPedro.setDiaSemana(DayOfWeek.MONDAY);
// //         dispPedro.setHoraInicio(LocalTime.of(10, 0));
// //         dispPedro.setHoraFim(LocalTime.of(17, 0));
// //         disponibilidadeRepository.save(dispPedro);

// //         // 4. Criar Cliente
// //         Cliente clienteAna = new Cliente();
// //         clienteAna.setNome("Ana Lúcia");
// //         clienteRepository.save(clienteAna);

// //         // 5. Criar Agendamento
// //         LocalDate hoje = LocalDate.of(2025, 6, 30);
// //         Agendamento agendamentoExistente = Agendamento.builder()
// //                 .barbeiro(barbeiroJoao)
// //                 .cliente(clienteAna)
// //                 .servico(servicoCorte)
// //                 .dataHoraInicio(hoje.atTime(10, 20))
// //                 .dataHoraFim(hoje.atTime(11, 0))
// //                 .status("Confirmado")
// //                 .build();
// //         agendamentoRepository.save(agendamentoExistente);

// //         System.out.println("--- Dados carregados com sucesso! ---");
// //     }
// // }