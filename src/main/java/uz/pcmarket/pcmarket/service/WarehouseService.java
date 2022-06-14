package uz.pcmarket.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pcmarket.pcmarket.entity.Address;
import uz.pcmarket.pcmarket.entity.Warehouse;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.payload.WarehouseDto;
import uz.pcmarket.pcmarket.repository.AddressRepository;
import uz.pcmarket.pcmarket.repository.WarehouseRepository;

import java.util.Optional;

@Service
public class WarehouseService {
    final WarehouseRepository warehouseRepository;
    final AddressRepository addressRepository;

    public WarehouseService(WarehouseRepository warehouseRepository, AddressRepository addressRepository) {
        this.warehouseRepository = warehouseRepository;
        this.addressRepository = addressRepository;
    }

    public ResponseEntity<Page<Warehouse>> getProducts(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(warehouseRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseEntity<Warehouse> getProduct(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        return optionalWarehouse.map(warehouse -> ResponseEntity.status(HttpStatus.OK).body(warehouse)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Message> addProduct(WarehouseDto warehouseDto) {
        boolean existsByName = warehouseRepository.existsByName(warehouseDto.getName());
        if (!existsByName) {
            Address savedAddress = addressRepository.save(new Address(warehouseDto.getCity(), warehouseDto.getDistrict(), warehouseDto.getStreet(), warehouseDto.getHomeNumber()));
            warehouseRepository.save(new Warehouse(warehouseDto.getName(), warehouseDto.isActive(), savedAddress));
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The warehouse was successfully added!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "This warehouse is already exits!"));
        }
    }

    public ResponseEntity<Message> editProduct(Integer id, WarehouseDto warehouseDto) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            Warehouse warehouse = optionalWarehouse.get();
            Address address = warehouse.getAddress();
            address.setCity(warehouseDto.getCity());
            address.setDistrict(warehouseDto.getDistrict());
            address.setStreet(warehouseDto.getStreet());
            address.setHomeNumber(warehouseDto.getHomeNumber());
            addressRepository.save(address);
            warehouse.setName(warehouseDto.getName());
            warehouse.setActive(warehouseDto.isActive());
            warehouseRepository.save(warehouse);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The warehouse was edited!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The warehouse is not found!"));
        }
    }

    public ResponseEntity<Message> deleteProduct(Integer id) {
        Optional<Warehouse> optionalWarehouse = warehouseRepository.findById(id);
        if (optionalWarehouse.isPresent()) {
            warehouseRepository.delete(optionalWarehouse.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The warehouse is deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The warehouse is not found!"));
        }
    }
}
