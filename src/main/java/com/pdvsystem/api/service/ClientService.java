package com.pdvsystem.api.service;

import com.pdvsystem.api.domain.client.Client;
import com.pdvsystem.api.domain.client.ClientRequestDTO;
import com.pdvsystem.api.repositories.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientService {

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

        return clientRepository.save(client);
    }

    /*
     * Busca todos os Clientes
     */

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    /*
     *Busca Cliente por ID
     */

    public Client getClientByID(UUID id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nenhum Cliente encontrado"));
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

        return clientRepository.save(client);
    }

    /*
     * Deleta Cliente
     */

    public void deleteClient(UUID id) {
        Client client = clientRepository.findById(id).orElseThrow(()-> new RuntimeException("Nenhum Cliente encontrado"));

        clientRepository.delete(client);
    }
}
