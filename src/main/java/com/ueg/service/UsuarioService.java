package com.ueg.service;

import com.ueg.model.Usuario;
import com.ueg.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository; 

    // incluir e alterar 
    public Usuario salvar(Usuario usuario) {
        return repository.save(usuario);
    }

    // listar 
    public List<Usuario> listarTodos() {
        return repository.findAll();
    }

    // consultar
    public Optional<Usuario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    // excluir
    public void excluir(Long id) {
        repository.deleteById(id);
    }
}