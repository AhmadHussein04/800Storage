package com.example._storageexam.service;

import com.example._storageexam.entity.Client;
import com.example._storageexam.entity.Product;
import com.example._storageexam.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> updateClient(int id, Client newClient) {
        Optional<Client> optionalClientt = clientRepository.findById(id);

        if (optionalClientt.isPresent()) {
            Client client = optionalClientt.get();
            client.setName(newClient.getName());
            client.setLast_name(newClient.getLast_name());
            client.setMobile(newClient.getMobile());
            clientRepository.save(client);
            return Optional.of(client);
        } else {
            return Optional.empty();
        }

    }

    public Optional<Client> getClientById(int id) {
        return clientRepository.findById(id);
    }
}