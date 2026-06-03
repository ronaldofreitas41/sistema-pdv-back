package com.pdvsystem.api.controller;



import com.pdvsystem.api.domain.suplier.Suplier;
import com.pdvsystem.api.domain.suplier.SuplierRequestDTO;
import com.pdvsystem.api.service.SuplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/suplier")
public class SuplierController {

    @Autowired
    private SuplierService suplierService;

    // GET - api/suplier/{id} - Busca um fornecedor por id
    @GetMapping("/{id}")
    public ResponseEntity<Suplier> getSuplierByID(@PathVariable UUID id) {
        Suplier suplier = suplierService.getSuplierByID(id);
        return ResponseEntity.ok(suplier);
    }

    //GET - api/suplier/pay - Busca os fornecedores
    @GetMapping
    public ResponseEntity<List<Suplier>> getAllSupliers() {
        List<Suplier> supliers = suplierService.getAllSupliers();
        return ResponseEntity.ok(supliers);
    }

    //POST - api/suplier/pay - Cria um novo fornecedor
    @PostMapping
    public ResponseEntity<Suplier> createSuplier(@RequestBody SuplierRequestDTO body) {
        Suplier suplier = suplierService.createSuplier(body);
        return ResponseEntity.ok(suplier);
    }

    //PUT - api/suplier/{id} - Edita um fornecedor
    @PutMapping("/{id}")
    public ResponseEntity<Suplier> editSuplier(@RequestBody SuplierRequestDTO body, @PathVariable UUID id) {
        Suplier suplier = suplierService.editSuplier(id,body);
        return ResponseEntity.ok(suplier);
    }

    //DELETE - api/suplier/{id} - Deleta um fornecedor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSuplier(@PathVariable UUID id) {
        suplierService.deleteSuplier(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }
}
