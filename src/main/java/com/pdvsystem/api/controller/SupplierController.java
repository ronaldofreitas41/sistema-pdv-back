package com.pdvsystem.api.controller;



import com.pdvsystem.api.domain.supplier.Supplier;
import com.pdvsystem.api.domain.supplier.SupplierRequestDTO;
import com.pdvsystem.api.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/supplier")
public class SupplierController {

    @Autowired
    private SupplierService supplierService;

    // GET - api/suplier/{id} - Busca um fornecedor por id
    @GetMapping("/{id}")
    public ResponseEntity<Supplier> getSuplierByID(@PathVariable UUID id) {
        Supplier supplier = supplierService.getSuplierByID(id);
        return ResponseEntity.ok(supplier);
    }

    //GET - api/supplier - Busca os fornecedores
    @GetMapping
    public ResponseEntity<List<Supplier>> getAllSupliers() {
        List<Supplier> suppliers = supplierService.getAllSupliers();
        return ResponseEntity.ok(suppliers);
    }

    //POST - api/supplier - Cria um novo fornecedor
    @PostMapping
    public ResponseEntity<Supplier> createSuplier(@RequestBody SupplierRequestDTO body) {
        Supplier supplier = supplierService.createSuplier(body);
        return ResponseEntity.ok(supplier);
    }

    //PUT - api/suplier/{id} - Edita um fornecedor
    @PutMapping("/{id}")
    public ResponseEntity<Supplier> editSuplier(@RequestBody SupplierRequestDTO body, @PathVariable UUID id) {
        Supplier supplier = supplierService.editSuplier(id,body);
        return ResponseEntity.ok(supplier);
    }

    //DELETE - api/suplier/{id} - Deleta um fornecedor
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSuplier(@PathVariable UUID id) {
        supplierService.deleteSuplier(id);
        return ResponseEntity.ok("Deletado com sucesso!");
    }
}
