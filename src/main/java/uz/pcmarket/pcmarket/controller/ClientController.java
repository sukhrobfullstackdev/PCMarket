package uz.pcmarket.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.pcmarket.entity.Client;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.service.ClientService;

@RestController
@RequestMapping(value = "/client")
public class ClientController {
    final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<Client>> getClients(@RequestParam int page, @RequestParam int size) {
        return clientService.getClients(page, size);
    }

    @PreAuthorize(value = "hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> getClient(@PathVariable Integer id) {
        return clientService.getClient(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Message> addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editClient(@PathVariable Integer id, @RequestBody Client client) {
        return clientService.editClient(id, client);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteClient(@PathVariable Integer id) {
        return clientService.deleteClient(id);
    }
}
