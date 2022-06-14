package uz.pcmarket.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.pcmarket.entity.Supplier;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.payload.SupplierDto;
import uz.pcmarket.pcmarket.service.SupplierService;

@RestController
@RequestMapping(value = "/supplier")
public class SupplierController {
    final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<Supplier>> getSuppliers(@RequestParam int page, @RequestParam int size) {
        return supplierService.getSuppliers(page, size);
    }

    @PreAuthorize(value = "hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Supplier> getSupplier(@PathVariable Integer id) {
        return supplierService.getSupplier(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Message> addSupplier(@RequestBody SupplierDto supplierDto) {
        return supplierService.addSupplier(supplierDto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editSupplier(@PathVariable Integer id, @RequestBody SupplierDto supplierDto) {
        return supplierService.editSupplier(id, supplierDto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteSupplier(@PathVariable Integer id) {
        return supplierService.deleteSupplier(id);
    }
}
