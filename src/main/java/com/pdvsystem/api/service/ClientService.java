package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.client.Client;
import com.pdvsystem.api.domain.client.ClientRequestDTO;
import com.pdvsystem.api.domain.client.ClientRequestSaleDTO;
import com.pdvsystem.api.infra.security.SecurityUtils;
import com.pdvsystem.api.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    /*
     * Cria Cliente
     */

    public Client createClient(ClientRequestDTO data) {
        Client client = new Client();

        client.setCpf(data.cpf());
        client.setName(data.name());
        client.setEmail(data.email());
        client.setTelefone(data.telefone());
        client.setEndereco(data.endereco());
        client.setCompanyId(SecurityUtils.getCompanyId());
        client.setCashback(data.cashback());
        client.setUltimaCompra(null);
        client.setValidadeCashback(null);
        client.setStatus(false);

        return clientRepository.save(client);
    }

    /*
     * Busca todos os Clientes
     */

    public List<Client> getAllClients() {
        return clientRepository.findByCompanyId(SecurityUtils.getCompanyId());
    }

    /*
     *Busca Cliente por ID
     */

    public Client getClientByID(UUID id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum Cliente encontrado"));

        if (!client.getCompanyId().equals(SecurityUtils.getCompanyId())) {
            throw new RuntimeException("Nenhum Cliente encontrado");
        }

        return client;
    }

    /*
     * Edita Cliente
     */

    public Client editClient(UUID id, ClientRequestDTO data) {
        Client client = getClientByID(id);

        client.setName(data.name());
        client.setEmail(data.email());
        client.setTelefone(data.telefone());
        client.setEndereco(data.endereco());
        client.setCpf(data.cpf());
        client.setCashback(data.cashback());
        client.setStatus(data.status());
        client.setUltimaCompra(null);
        client.setValidadeCashback(null);

        return clientRepository.save(client);
    }
    public Client editClientVenda(UUID id, ClientRequestSaleDTO data) {
        Client client = getClientByID(id);

        client.setName(data.name());
        client.setEmail(data.email());
        client.setTelefone(data.telefone());
        client.setEndereco(data.endereco());
        client.setCpf(data.cpf());
        client.setCashback(data.cashback());
        client.setStatus(data.status());
        client.setUltimaCompra(data.ultimaCompra());
        Date validade = data.validadeCashback();
        LocalDate novaData = validade.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .plusDays(45);

        Date novaValidade = Date.from(
                novaData.atStartOfDay(ZoneId.systemDefault()).toInstant()
        );
        client.setValidadeCashback(novaValidade);

        return clientRepository.save(client);
    }

    /*
     * Deleta Cliente
     */

    public void deleteClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new RuntimeException("Nenhum Cliente encontrado"));

        clientRepository.delete(client);
    }
}
