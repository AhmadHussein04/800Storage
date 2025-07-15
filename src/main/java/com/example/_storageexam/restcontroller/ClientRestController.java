package com.example._storageexam.restcontroller;


import com.example._storageexam.entity.Client;
import com.example._storageexam.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("800Storage/clients")
public class ClientRestController {

    @Autowired
    private ClientService clientService;

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok(clientService.getAllClients());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getClientById(@PathVariable int id) {
        Optional<Client> client = clientService.getClientById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else {
            return ResponseEntity.status(404).body("Client not found with id: " + id);
        }
    }

    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody Client client) {
        return ResponseEntity.ok(clientService.createClient(client));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> updateClient(@PathVariable int id, @RequestBody Client newClient) {
        Optional<Client> updatedClient = clientService.updateClient(id, newClient);
        if (updatedClient.isPresent()) {
            return ResponseEntity.ok(updatedClient.get());
        } else {
            return ResponseEntity.status(404).body("Client not found with id: " + id);
        }
    }
}