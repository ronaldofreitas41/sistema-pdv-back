package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.client.Client;
import com.pdvsystem.api.domain.count.CountRequestDTO;
import com.pdvsystem.api.domain.product.Product;
import com.pdvsystem.api.domain.sale.*;
import com.pdvsystem.api.domain.user.User;
import com.pdvsystem.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
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

    @Autowired
    private CountService countService;

    public Sale createSale(SaleRequestDTO data) {

        Client client = clientRepository.findById(data.clientId())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        User user = userRepository.findById(data.userID())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        Sale sale = new Sale();

        sale.setClient(client);
        sale.setUserID(user.getId());
        sale.setUserName(user.getName());
        sale.setCompanyId(user.getCompanyId());
        sale.setCashBack(data.cashBack());
        sale.setTotal(data.total());
        sale.setFormaPagamento(data.formaPagamento());
        sale.setCreatedAt(LocalDateTime.now());

        List<SaleItem> items = new ArrayList<>();
        List<Product> updatedProducts = new ArrayList<>();

        for (SaleItemRequestDTO itemDTO : data.items()) {

            Product product = productRepository.findById(itemDTO.productId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));

            if (product.getEstoque() == null) {
                product.setEstoque(0);
            }
            if (itemDTO.quantity() > product.getEstoque()) {
                throw new RuntimeException("Estoque insuficiente para o produto " + product.getNome());
            }

            product.setEstoque(product.getEstoque() - itemDTO.quantity());
            updatedProducts.add(product);

            SaleItem item = new SaleItem();
            item.setSale(sale);
            item.setProduct(product);
            item.setQuantity(itemDTO.quantity());
            item.setUnitPrice(itemDTO.unitPrice());

            items.add(item);
        }

        sale.setItems(items);
        Sale savedSale = saleRepository.save(sale);
        productRepository.saveAll(updatedProducts);

        String payment = data.formaPagamento();
        String countStatus = "PENDENTE";
        if (payment != null) {
            String normalized = payment.toUpperCase();
            if (normalized.contains("DINHEIRO") || normalized.contains("PIX") || normalized.contains("CARTAO") || normalized.contains("CARTÃO")) {
                countStatus = "RECEBIDO";
            }
        }

        CountRequestDTO countRequest = new CountRequestDTO(
                "Venda " + savedSale.getId() + " - " + savedSale.getUserName(),
                "Venda",
                countStatus,
                savedSale.getTotal(),
                new Date(),
                "RECIEVE",
                savedSale.getUserID(),
                savedSale.getCompanyId(),
                null,
                client.getId()
        );
        countService.createCountRecieve(countRequest);
        return savedSale;
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

    public List<Sale> getSalesByUserId(String userID) {
        return saleRepository.findByUserID(userID);
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