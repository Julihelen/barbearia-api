package br.com.ifpe.barbearia_api.modelo.barbeiro;

import br.com.ifpe.barbearia_api.api.barbeiro.BarbeiroComDisponibilidadeDTO;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoRepository;
import br.com.ifpe.barbearia_api.modelo.agendamento.AgendamentoService;
import br.com.ifpe.barbearia_api.modelo.barbeiro.DisponibilidadeRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;
    private final DisponibilidadeRepository disponibilidadeRepository;
    private final AgendamentoService agendamentoService;

    public Barbeiro obterPorID(Long id) {
        return barbeiroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado com ID: " + id));
    }

    @Transactional
    public Barbeiro salvar(Barbeiro barbeiro) {
        return barbeiroRepository.save(barbeiro);
    }
    
    @Transactional
    public Barbeiro associarServicos(Long barbeiroId, Set<Long> servicoIds) {
        Barbeiro barbeiro = obterPorID(barbeiroId);
        Set<Servico> servicosParaAssociar = new HashSet<>(servicoRepository.findAllById(servicoIds));
        barbeiro.setServicos(servicosParaAssociar);
        return barbeiroRepository.save(barbeiro);
    }

    public List<Barbeiro> listarTodos() {
        return barbeiroRepository.findAll();
    }

    public List<Barbeiro> buscarPorIdServico(Long idServico) {
        return barbeiroRepository.buscarPorIdServico(idServico);
    }

    public List<LocalTime> obterHorariosDisponiveis(Long barbeiroId, LocalDate data) {
        return agendamentoService.getHorariosDisponiveis(barbeiroId, data);
    }
}
