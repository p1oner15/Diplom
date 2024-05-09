package com.example.diplom.service;
import com.example.diplom.domain.Client;
import com.example.diplom.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public Client updateClient(Long id, Client updatedClient) {
        if (clientRepository.existsById(id)) {
            updatedClient.setClientId(id);
            return clientRepository.save(updatedClient);
        }
        return null;
    }

    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }
}