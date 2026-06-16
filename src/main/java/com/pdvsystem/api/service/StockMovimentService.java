package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.product.Product;
import com.pdvsystem.api.domain.moviment.StockMoviment;
import com.pdvsystem.api.domain.moviment.StockMovimentDTO;
import com.pdvsystem.api.repositories.ProductRepository;
import com.pdvsystem.api.repositories.StockMovimentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StockMovimentService {

    @Autowired
    private StockMovimentRepository repository;

    @Autowired
    private ProductRepository productRepository;

    public StockMoviment create(StockMovimentDTO data) {

        Product product = productRepository.findById(data.productId())
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        StockMoviment moviment = new StockMoviment();

        moviment.setType(data.type());
        moviment.setQuantidade(data.quantidade());
        moviment.setProduct(product);

        return repository.save(moviment);
    }

    public List<StockMoviment> getAll() {
        return repository.findAll();
    }

    public StockMoviment getById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Movimentação não encontrada"));
    }

    public void deleteMoviment(UUID id) {
        repository.deleteById(id);
    }
}