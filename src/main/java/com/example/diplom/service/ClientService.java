package com.example.diplom.service;

import com.example.diplom.domain.Client;
import com.example.diplom.domain.RegisterDto;
import com.example.diplom.repository.ClientRepository;
import com.example.diplom.utils.PasswordUtil;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class ClientService {

    private final ClientRepository clientRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    public Client createClient(Client client) {
        // Сохраняем пароль в открытом виде
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

    public Client authenticateClient(String email, String password) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && PasswordUtil.verifyPassword(password, client.getPassword())) {
            return client;
        }
        return null;
    }

    public boolean checkEmailExists(String email) {
        return clientRepository.existsByEmail(email);
    }

    public boolean checkPasswordMatches(String email, String password) {
        // Ищем пользователя по email и паролю
        return clientRepository.existsByEmailAndPassword(email, password);
    }

    public Client registerClient(RegisterDto client) {
        if (!clientRepository.existsByEmail(client.getEmail())) {
            String hashedPassword = PasswordUtil.hashPassword(client.getPassword());
            client.setPassword(hashedPassword);
            Client newClient = Client.builder()
                    .firstName(client.getFirstName())
                    .lastName(client.getLastName())
                    .email(client.getEmail())
                    .password(hashedPassword)
                    .passport(client.getPassport())
                    .citizenship(client.getCitizenship())
                    .dateOfBirth(client.getDateOfBirth())
                    .phoneNumber(client.getPhoneNumber())
                    .build();
            return clientRepository.save(newClient);
        } else {
            // Если пользователь с таким email уже существует
            throw new RuntimeException("User with this email already exists");
        }
    }
}
