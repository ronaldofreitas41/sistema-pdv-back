package com.pdvsystem.api.controller;


import com.pdvsystem.api.domain.client.Client;
import com.pdvsystem.api.domain.client.ClientRequestDTO;
import com.pdvsystem.api.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {

        @Autowired
        private ClientService clientService;

        // GET - api/client/{id} - Busca um cliente por id
        @GetMapping("/{id}")
        public ResponseEntity<Client> getClientByID(@PathVariable UUID id) {
            Client client = clientService.getClientByID(id);
            return ResponseEntity.ok(client);
        }

        //GET - api/client/pay - Busca os clientes
        @GetMapping
        public ResponseEntity<List<Client>> getAllClients() {
            List<Client> clients = clientService.getAllClients();
            return ResponseEntity.ok(clients);
        }

        //POST - api/client/pay - Cria um novo cliente
        @PostMapping
        public ResponseEntity<Client> createClient(@RequestBody ClientRequestDTO body) {
            Client client = clientService.createClient(body);
            return ResponseEntity.ok(client);
        }

        //PUT - api/client/{id} - Edita um cliente
        public ResponseEntity<Client> editClient(@RequestBody ClientRequestDTO body, @PathVariable UUID id) {
            Client client = clientService.editClient(id,body);
            return ResponseEntity.ok(client);
        }

        //DELETE - api/client/{id} - Deleta um cliente
        public ResponseEntity<String> deleteClient(@PathVariable UUID id) {
            clientService.deleteClient(id);
            return ResponseEntity.ok("Deletado com sucesso!");
        }


}
