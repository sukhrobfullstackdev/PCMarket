package uz.pcmarket.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.pcmarket.entity.Warehouse;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.payload.WarehouseDto;
import uz.pcmarket.pcmarket.service.WarehouseService;

import java.util.List;

@RestController
@RequestMapping(value = "/warehouse")
public class WarehouseController {
    final WarehouseService warehouseService;

    public WarehouseController(WarehouseService warehouseService) {
        this.warehouseService = warehouseService;
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<Warehouse>> getProducts(@RequestParam int page, @RequestParam int size) {
        return warehouseService.getProducts(page, size);
    }

    @PreAuthorize(value = "hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Warehouse> getProduct(@PathVariable Integer id) {
        return warehouseService.getProduct(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Message> addProduct(@RequestBody WarehouseDto warehouseDto) {
        return warehouseService.addProduct(warehouseDto);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editProduct(@PathVariable Integer id, @RequestBody WarehouseDto warehouseDto) {
        return warehouseService.editProduct(id, warehouseDto);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteProduct(@PathVariable Integer id) {
        return warehouseService.deleteProduct(id);
    }
}
