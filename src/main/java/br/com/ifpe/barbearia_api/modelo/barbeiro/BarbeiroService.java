package br.com.ifpe.barbearia_api.modelo.barbeiro;

import br.com.ifpe.barbearia_api.modelo.servicos.Servico;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class BarbeiroService {

    private final BarbeiroRepository barbeiroRepository;
    private final ServicoRepository servicoRepository;

    @Transactional
    public Barbeiro salvar(Barbeiro barbeiro) {
        // Apenas salva a entidade com os dados básicos
        return barbeiroRepository.save(barbeiro);
    }
    
    @Transactional
    public Barbeiro associarServicos(Long barbeiroId, Set<Long> servicoIds) {
        // Busca o barbeiro no banco
        Barbeiro barbeiro = barbeiroRepository.findById(barbeiroId)
                .orElseThrow(() -> new RuntimeException("Barbeiro não encontrado!"));
        
        // Busca os serviços a serem associados
        Set<Servico> servicosParaAssociar = new HashSet<>(servicoRepository.findAllById(servicoIds));

        // Associa os serviços e salva
        barbeiro.setServicos(servicosParaAssociar);
        return barbeiroRepository.save(barbeiro);
    }

    public List<Barbeiro> listarTodos() {
        return barbeiroRepository.findAll();
    }
}