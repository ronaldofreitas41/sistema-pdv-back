package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.product.ProductRequestDTO;
import com.pdvsystem.api.domain.product.Produto;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    /*
    * Metodo para criação da estrutura de produto
    */
    public Produto createProduct(ProductRequestDTO data){

        Produto produto = new Produto();

        produto.setCategoria(data.categoria());
        produto.setCodigo(data.codigo());
        produto.setCusto(data.custo());
        produto.setEstoque(data.estoque());
        produto.setEstoqueMin(data.estoqueMin());
        produto.setPreco(data.preco());
        produto.setNome(data.nome());

        return produto;
    }
}
