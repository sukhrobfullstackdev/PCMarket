package uz.pcmarket.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pcmarket.pcmarket.entity.Client;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {
    final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ResponseEntity<Page<Client>> getClients(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseEntity<Client> getClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        return optionalClient.map(client -> ResponseEntity.status(HttpStatus.OK).body(client)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Message> addClient(Client client) {
        boolean exists = clientRepository.existsByName(client.getName());
        if (!exists) {
            clientRepository.save(client);
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The client is added!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The client is already exists!"));
        }
    }

    public ResponseEntity<Message> editClient(Integer id, Client client) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            Client editingClient = optionalClient.get();
            editingClient.setName(client.getName());
            editingClient.setPhoneNumber(client.getPhoneNumber());
            clientRepository.save(editingClient);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The client was edited!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The client is not found!"));
        }
    }

    public ResponseEntity<Message> deleteClient(Integer id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isPresent()) {
            clientRepository.delete(optionalClient.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The client was deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The client is not found!"));
        }
    }
}
