package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.moviment.CashMoviment;
import com.pdvsystem.api.domain.moviment.CashMovimentRequestDTO;
import com.pdvsystem.api.service.CashMovimentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/cashmoviment")
public class CashMovimentController {

    @Autowired
    private CashMovimentService service;

    @PostMapping
    public ResponseEntity<CashMoviment> create(@RequestBody CashMovimentRequestDTO body) {
        return ResponseEntity.ok(service.create(body));
    }

    @GetMapping
    public ResponseEntity<List<CashMoviment>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CashMoviment> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.deleteMoviment(id);
        return ResponseEntity.ok("Movimentação removida");
    }
}