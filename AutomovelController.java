package com.ueg.controller.copy;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.ueg.model.Automovel;
import com.ueg.service.AutomovelService;

@RestController
@RequestMapping("/api/automoveis") // Define a rota base para este recurso
public class AutomovelController {

    @Autowired
    private AutomovelService service;

    // Endpoint para Listar: Responde a requisições GET em /api/automoveis
    @GetMapping
    public List<Automovel> listar() {
        return service.listarTodos();
        // Retorna a lista completa de automóveis em formato JSON.
    }

    // Endpoint para Consultar: Responde a requisições GET em /api/automoveis/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Automovel> consultar(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
        // Tenta encontrar o automóvel. Se achar, retorna 200 OK; se não, retorna 404 Not Found.
    }

    // Endpoint para Incluir: Responde a requisições POST em /api/automoveis
    @PostMapping
    public Automovel incluir(@RequestBody Automovel automovel) {
        return service.salvar(automovel);
        // Recebe o JSON do automóvel no corpo da requisição e o persiste no banco.
    }
}
