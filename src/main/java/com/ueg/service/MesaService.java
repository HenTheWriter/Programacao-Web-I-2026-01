package com.ueg.service;

import com.ueg.model.Mesa;
import com.ueg.repository.MesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class MesaService {

    @Autowired
    private MesaRepository repository; 

    // incluir e alterar 
    public Mesa salvar(Mesa mesa) {
        
        return repository.save(mesa);
    }

    // listar 
    public List<Mesa> listarTodos() {
        return repository.findAll();
    }

    // consultar
    public Optional<Mesa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // excluir
    public void excluir(Long id) {
        repository.deleteById(id);
    }

    // convite para mesa
    public Mesa salvarMesa(Mesa mesa) {
        // Gera um código de convite automático se estiver vazio
        if (mesa.getCodigoConvite() == null || mesa.getCodigoConvite().isEmpty()) {
            mesa.setCodigoConvite("MESA-" + System.currentTimeMillis() % 1000);
        }
        return repository.save(mesa);
    }
}