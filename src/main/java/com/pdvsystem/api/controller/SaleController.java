package com.pdvsystem.api.controller;

import com.pdvsystem.api.domain.sale.*;
import com.pdvsystem.api.service.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/sale")
public class SaleController {

    @Autowired
    private SaleService service;

    @PostMapping
    public ResponseEntity<Sale> create(@RequestBody SaleRequestDTO body) {
        return ResponseEntity.ok(service.createSale(body));
    }

    @GetMapping("/{id}/itens")
    public ResponseEntity<List<SaleItemResponseDTO>> getItensOnSale(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getItensOnSale(id));
    }

    @GetMapping
    public ResponseEntity<List<Sale>> getAllSales() {
        return ResponseEntity.ok(service.getAllSales());
    }

    @GetMapping("/items/soma")
    public ResponseEntity<List<VendasTotaisDTO>> getSoma() {
        return ResponseEntity.ok(service.getVendasTotais());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sale> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(service.getSaleById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        service.deleteSale(id);
        return ResponseEntity.ok("Venda removida com sucesso");
    }



}
