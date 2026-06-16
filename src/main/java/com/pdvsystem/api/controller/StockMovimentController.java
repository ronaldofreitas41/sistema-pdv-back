package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.moviment.StockMoviment;
import com.pdvsystem.api.domain.moviment.StockMovimentDTO;
import com.pdvsystem.api.service.StockMovimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stockmoviment")

public class StockMovimentController {

    @Autowired
    private StockMovimentService service;

    @PostMapping
    public ResponseEntity<StockMoviment> create(@RequestBody StockMovimentDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @GetMapping
    public ResponseEntity<List<StockMoviment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StockMoviment> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMoviment(@PathVariable UUID id) {
        service.deleteMoviment(id);
        return ResponseEntity.ok("Movimentação removida");
    }
}
