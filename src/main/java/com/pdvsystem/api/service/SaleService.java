package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.client.Client;
import com.pdvsystem.api.domain.product.Product;
import com.pdvsystem.api.domain.sale.*;
import com.pdvsystem.api.domain.user.User;
import com.pdvsystem.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleItemRepository saleItemRepository;

    public Sale createSale(SaleRequestDTO data) {

        Client client = clientRepository.findById(data.clientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        User user = userRepository.findById(data.userId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Sale sale = new Sale();

        sale.setClient(client);
        sale.setUser(user);
        sale.setCashBack(data.cashBack());
        sale.setTotal(data.total());
        sale.setCreatedAt(LocalDateTime.now());

        List<SaleItem> items = new ArrayList<>();

        for (SaleItemRequestDTO itemDTO : data.items()) {

            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            SaleItem item = new SaleItem();

            item.setSale(sale);
            item.setProduct(product);
            item.setQuantity(itemDTO.quantity());
            item.setUnitPrice(itemDTO.unitPrice());

            items.add(item);
        }

        sale.setItems(items);

        return saleRepository.save(sale);
    }

    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    public Sale getSaleById(UUID id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venda não encontrada"));
    }

    public void deleteSale(UUID id) {
        Sale sale = getSaleById(id);
        saleRepository.delete(sale);
    }

    public List<SaleItemResponseDTO> getItensOnSale(UUID id) {
        Sale sale = getSaleById(id);

        return sale.getItems()
                .stream()
                .map(item -> new SaleItemResponseDTO(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getProduct().getNome(),
                        item.getQuantity(),
                        item.getUnitPrice()
                ))
                .toList();
    }

    public List<VendasTotaisDTO> getVendasTotais(){
        List<SaleItem> itens = saleItemRepository.findAll();
        List<VendasTotaisDTO> vendasTotais  = itens.stream()
                .collect(Collectors.groupingBy(SaleItem::getProduct))
                .entrySet()
                .stream()
                .map(entry -> {
                    Product produto = entry.getKey();
                    List<SaleItem> itensProduto = entry.getValue();

                    int quantidadeTotal = itensProduto.stream()
                            .mapToInt(SaleItem::getQuantity)
                            .sum();

                    double valorTotal = itensProduto.stream()
                            .mapToDouble(item ->
                                    item.getQuantity() * produto.getPreco()
                            )
                            .sum();

                    double lucro = itensProduto.stream()
                            .mapToDouble(item ->
                                    item.getQuantity() * produto.getCusto()
                            ).sum();

                    lucro = valorTotal - lucro;
                    return new VendasTotaisDTO(
                            quantidadeTotal,
                            valorTotal,
                            lucro,
                            produto.getNome(),
                            produto.getId()
                    );
                })
                .toList();
        return vendasTotais;
    }
}