package br.com.ifpe.barbearia_api.modelo.servicos;
import br.com.ifpe.barbearia_api.modelo.servicos.ServicoService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class ServicoService {
    
    @Autowired
    private ServicoRepository repository;

   @Transactional
    public Servico save(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("O objeto Servico não pode ser nulo.");
        }
        servico.setHabilitado(Boolean.TRUE);
        return repository.save(servico);
    }


    public List<Servico> listarTodos() {
  
        return repository.findAll(); //SELECT * FROM Servico
    }

    public Servico obterPorID(Long id) {

        return repository.findById(id).get(); //SELECT * FROM Servico WHERE id = ?
    }

    @Transactional
    public void update(Long id, Servico servicoAlterado) {

        Servico servico = repository.findById(id).get();

        servico.setTitulo(servicoAlterado.getTitulo());
        servico.setDescricao(servicoAlterado.getDescricao());
        servico.setPreco(servicoAlterado.getPreco());
            
        repository.save(servico);
    }

    @Transactional
    public void delete(Long id) {

        Servico servico = repository.findById(id).get();
        servico.setHabilitado(Boolean.FALSE);

        repository.save(servico);
    }
    
    

}