package com.ueg.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ueg.model.Automovel;
import com.ueg.repository.AutomovelRepository;

@Service
public class AutomovelService 
{
    @Autowired
    private AutomovelRepository repository;

    public List<Automovel> listarTodos() 
    {
        return repository.findAll();
    }

    public Optional<Automovel> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Automovel salvar(Automovel automovel) {
        return repository.save(automovel);
    }
}