package br.com.ifpe.barbearia_api.modelo.barbeiro;

import br.com.ifpe.barbearia_api.api.barbeiro.BarbeiroComDisponibilidadeDTO;
import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoRepository;
import br.com.ifpe.barbearia_api.modelo.agendamento.AgendamentoService;
import br.com.ifpe.barbearia_api.modelo.barbeiro.DisponibilidadeRepository;
import lombok.RequiredArgsConstructor;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
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


    @Transactional
    public Barbeiro salvar(Barbeiro barbeiro) {
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

   public Barbeiro atualizar(Long id, Barbeiro barbeiroAtualizado) {
    return barbeiroRepository.findById(id)
        .map(barbeiroExistente -> {
            barbeiroExistente.setNome(barbeiroAtualizado.getNome());
            barbeiroExistente.setFoneCelular(barbeiroAtualizado.getFoneCelular());
            barbeiroExistente.setEmail(barbeiroAtualizado.getEmail());
            barbeiroExistente.setDataNascimento(barbeiroAtualizado.getDataNascimento());
            barbeiroExistente.setCpf(barbeiroAtualizado.getCpf());
            barbeiroExistente.setEndereco(barbeiroAtualizado.getEndereco());
            barbeiroExistente.setAtendimentoInicio(barbeiroAtualizado.getAtendimentoInicio());
            barbeiroExistente.setAtendimentoFim(barbeiroAtualizado.getAtendimentoFim());
            barbeiroExistente.setSenha(barbeiroAtualizado.getSenha());

            // // Apenas atualiza os IDs dos serviços diretamente
            // if (barbeiroAtualizado.getServicoIds() != null) {
            //     barbeiroExistente.setServicoIds(barbeiroAtualizado.getServicoIds());
            // }

            return barbeiroRepository.save(barbeiroExistente);
        })
        .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado com id: " + id));
    }


    public void deletar(Long id) {
        if (!barbeiroRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Barbeiro não encontrado");
        }
        barbeiroRepository.deleteById(id);
    }
    @Transactional
    public Barbeiro associarServicos(Long barbeiroId, Set<Long> servicoIds) {
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado"));

        // Buscar os objetos Servico para os IDs recebidos
        Set<Servico> servicos = servicoRepository.findAllById(servicoIds).stream()
                .collect(Collectors.toSet());

        barbeiro.setServicos(servicos);

        return barbeiroRepository.save(barbeiro);
    }

    public Barbeiro obterPorID(Long id) {
        return barbeiroRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado com ID: " + id));
    }
    public List<Barbeiro> getBarbeirosPorServico(Long idServico) {
        return barbeiroRepository.buscarPorIdServico(idServico);
    }
}
