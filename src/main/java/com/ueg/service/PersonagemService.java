package com.ueg.service;

import com.ueg.model.Personagem;
import com.ueg.repository.PersonagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PersonagemService {

    @Autowired
    private PersonagemRepository repository; 

    // incluir e alterar 
    public Personagem salvar(Personagem personagem) {
        
        return repository.save(personagem);
    }

    // listar 
    public List<Personagem> listarTodos() {
        return repository.findAll();
    }

    // consultar
    public Optional<Personagem> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // excluir
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    public Personagem inserirNaCampanha(Long id) {
        Personagem p = repository.findById(id)
            .orElseThrow(() -> new RuntimeException("Personagem não encontrado"));

        
        if (p.isInserido()) {
            throw new RuntimeException("Este personagem já está em uma campanha!");
        }

        p.setInserido(true); // Altera o valor
       
        return repository.save(p);
    }
}