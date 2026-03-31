package com.ueg.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ueg.exception.ResourceNotFoundException;
import com.ueg.model.*;
import com.ueg.repository.PersonagemRepository;
import com.ueg.service.PersonagemService;

@RestController
@RequestMapping("/personagens")
@CrossOrigin(origins="*")
public class PersonagemController {

    @Autowired
    private PersonagemRepository autorep;

    @Autowired
    private PersonagemService service;

    @GetMapping
    public List<Personagem> listar() {
        return autorep.findAll(Sort.by(Sort.Direction.DESC, "id"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Personagem> consultar(@PathVariable Long id) {
        Personagem auto = autorep.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Personagem nao encontrado."));
        return ResponseEntity.ok(auto);
    }

    @PostMapping
    public Personagem incluir(@RequestBody Personagem personagem) {
        return autorep.save(personagem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> excluir(@PathVariable Long id) {
        Personagem auto = autorep.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Personagem nao encontrado."));
        autorep.delete(auto);
        Map<String, Boolean> resposta = new HashMap<>();
        resposta.put("Personagem Excluido!", true);
        return ResponseEntity.ok(resposta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Personagem> alterar(@PathVariable Long id, @RequestBody Personagem personagem) {
        Personagem auto = autorep.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Personagem nao encontrado."));

        auto.setNEX(personagem.getNEX());
        auto.setNome(personagem.getNome());
        auto.setClasse(personagem.getClasse());
        auto.setSaH(personagem.getSaH());

        auto.setAgiATR(personagem.getAgiATR());
        auto.setForATR(personagem.getForATR());
        auto.setIntATR(personagem.getIntATR());
        auto.setPreATR(personagem.getPreATR());
        auto.setVigATR(personagem.getVigATR());

        auto.setPvAtual(personagem.getPvAtual());
        auto.setPvMAX(personagem.getPvMAX());
        auto.setSanAtual(personagem.getSanAtual());
        auto.setSanMAX(personagem.getSanMAX());
        auto.setPeAtual(personagem.getPeAtual());
        auto.setPeMAX(personagem.getPeMAX());
        auto.setPdAtual(personagem.getPdAtual());
        auto.setPdMAX(personagem.getPdMAX());

        Personagem atualizado = autorep.save(auto);
        return ResponseEntity.ok(atualizado);
    }

    // Corrigido: caminho distinto para evitar conflito com alterar()
    @PutMapping("/{id}/campanha")
    public ResponseEntity<Personagem> inserirNaCampanha(@PathVariable Long id) {
        Personagem atualizado = service.inserirNaCampanha(id);
        return ResponseEntity.ok(atualizado);
    }
}