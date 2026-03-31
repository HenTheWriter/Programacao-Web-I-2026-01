package com.ueg.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ueg.model.Mesa;
import com.ueg.repository.MesaRepository;
import com.ueg.service.MesaService;

@RestController
@RequestMapping("/mesas")
@CrossOrigin(origins = "*")
public class MesaController {

    @Autowired
    private MesaRepository repository;

    @Autowired
    private MesaService service;

    @GetMapping
    public List<Mesa> listar() {
        return repository.findAll();
    }

    @PostMapping
    public Mesa criarMesa(@RequestBody Mesa mesa) {
        return service.salvarMesa(mesa);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> buscarPorId(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mesa> editarMesa(@PathVariable Long id, @RequestBody Mesa mesaAtualizada) {
        return repository.findById(id).map(mesa -> {
            mesa.setNomeDaMesa(mesaAtualizada.getNomeDaMesa());
            mesa.setSistema(mesaAtualizada.getSistema());
            mesa.setMaxJogadores(mesaAtualizada.getMaxJogadores());
            mesa.setPermiteEspectadores(mesaAtualizada.isPermiteEspectadores());
            return ResponseEntity.ok(repository.save(mesa));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarMesa(@PathVariable Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}