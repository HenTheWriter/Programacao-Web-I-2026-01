package com.ueg.controller;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ueg.exception.ResourceNotFoundException;
import com.ueg.model.Usuario;
import com.ueg.repository.UsuarioRepository;
import com.ueg.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin(origins="*")
public class UsuarioController {
    
    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private UsuarioService service;
    
    @GetMapping
    public List<Usuario> listar() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> consultar(@PathVariable Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("Usuário não encontrado."));
        return ResponseEntity.ok(usuario);
    }
    
    @PostMapping
    public Usuario incluir(@RequestBody Usuario usuario) {
        usuario.setDataCadastro(LocalDate.now());
        return repository.save(usuario);
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Usuario usuario = repository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("Usuário não encontrado."));
        
        repository.delete(usuario);
        
        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Usuário Excluído!", true);
        return ResponseEntity.ok(resposta);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> alterar(@PathVariable Long id, @RequestBody Usuario dadosAtualizados) {
        Usuario usuario = repository.findById(id).orElseThrow(()->
            new ResourceNotFoundException("Usuário não encontrado."));
        
        usuario.setLogin(dadosAtualizados.getLogin());
        usuario.setSenha(dadosAtualizados.getSenha());
        usuario.setEmail(dadosAtualizados.getEmail());
        usuario.setIdade(dadosAtualizados.getIdade());
        // A data de cadastro não muda no PUT!
        
        Usuario atualizado = repository.save(usuario);
        return ResponseEntity.ok(atualizado);
    }
}