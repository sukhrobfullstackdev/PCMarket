package uz.pcmarket.pcmarket.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.pcmarket.entity.Computer;
import uz.pcmarket.pcmarket.payload.ComputerDto;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.service.ComputerService;

@RestController
@RequestMapping(value = "/computer")
public class ComputerController {
    final ComputerService computerService;

    public ComputerController(ComputerService computerService) {
        this.computerService = computerService;
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<Computer>> getComputers(@RequestParam int page, @RequestParam int size) {
        return computerService.getComputers(page, size);
    }

    @PreAuthorize(value = "hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Computer> getComputer(@PathVariable Integer id) {
        return computerService.getComputer(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Message> addComputer(@RequestBody ComputerDto computerDto) {
        return computerService.addComputer(computerDto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editComputer(@PathVariable Integer id, @RequestBody ComputerDto computerDto) {
        return computerService.editComputer(id, computerDto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteComputer(@PathVariable Integer id) {
        return computerService.deleteComputer(id);
    }
}
