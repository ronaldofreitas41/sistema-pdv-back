package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.product.ProductRequestDTO;
import com.pdvsystem.api.domain.product.Product;
import com.pdvsystem.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    /*
     * Criação de produtos
     */
    public Product createProduct(ProductRequestDTO data) {

        Product product = new Product();

        product.setCategoria(data.categoria());
        product.setCodigo(data.codigo());
        product.setCusto(data.custo());
        product.setEstoque(data.estoque());
        product.setEstoqueMin(data.estoqueMin());
        product.setPreco(data.preco());
        product.setNome(data.nome());

        return productRepository.save(product);
    }

    /*
     * Buscar Produtos por Id
     */
    public Product getProductById(UUID id) {
        return productRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Produto não localizado"));
    }

    /*
     * Buscar Produtos
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /*
     * Atualizar produto
     */
    public Product updateProduct(UUID id, ProductRequestDTO data) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        product.setNome(data.nome());
        product.setCodigo(data.codigo());
        product.setPreco(data.preco());
        product.setCusto(data.custo());
        product.setEstoque(data.estoque());
        product.setEstoqueMin(data.estoqueMin());
        product.setCategoria(data.categoria());

        return productRepository.save(product);
    }

    /*
     * Delete de Produtos
     */
    public void deleteProduct(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

        productRepository.delete(product);
    }
}
