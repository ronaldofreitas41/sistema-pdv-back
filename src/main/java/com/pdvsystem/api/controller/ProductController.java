package com.pdvsystem.api.controller;


import com.pdvsystem.api.domain.product.Product;
import com.pdvsystem.api.domain.product.ProductRequestDTO;
import com.pdvsystem.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    // POST /api/product - criar produto
    @PostMapping
    public ResponseEntity<Product> create(@RequestBody ProductRequestDTO body) {
        Product product = productService.createProduct(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    // GET /api/product/{id} — buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(product);
    }

    // GET /api/product — listar todos
    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    // PUT /api/product/{id} — atualizar produto
    @PutMapping("/{id}")
    public ResponseEntity<Product> update(
            @PathVariable UUID id,
            @RequestBody ProductRequestDTO body
    ) {
        Product product = productService.updateProduct(id, body);
        return ResponseEntity.ok(product);
    }

    // DELETE /api/product/{id} — deletar produto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}
