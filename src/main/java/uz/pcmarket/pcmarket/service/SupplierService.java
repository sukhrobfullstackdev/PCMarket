package uz.pcmarket.pcmarket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pcmarket.pcmarket.entity.Supplier;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.payload.SupplierDto;
import uz.pcmarket.pcmarket.repository.SupplierRepository;

import java.util.Optional;

@Service
public class SupplierService {
    final SupplierRepository supplierRepository;

    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public ResponseEntity<Page<Supplier>> getSuppliers(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(supplierRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseEntity<Supplier> getSupplier(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        return optionalSupplier.map(supplier -> ResponseEntity.status(HttpStatus.OK).body(supplier)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Message> addSupplier(SupplierDto supplierDto) {
        boolean exists = supplierRepository.existsByName(supplierDto.getName());
        if (!exists) {
            supplierRepository.save(new Supplier(supplierDto.getName(), supplierDto.isActive(), supplierDto.getPhoneNumber()));
            return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The supplier was added!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The supplier is already exists!"));
        }
    }

    public ResponseEntity<Message> editSupplier(Integer id, SupplierDto supplierDto) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            boolean exists = supplierRepository.existsByNameAndIdNot(supplierDto.getName(), id);
            if (!exists) {
                Supplier supplier = optionalSupplier.get();
                supplier.setName(supplierDto.getName());
                supplier.setActive(supplierDto.isActive());
                supplier.setPhoneNumber(supplierDto.getPhoneNumber());
                supplierRepository.save(supplier);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The supplier is edited!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The supplier name is already in use!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The supplier is not found!"));
        }
    }

    public ResponseEntity<Message> deleteSupplier(Integer id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            supplierRepository.delete(optionalSupplier.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The supplier was deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The supplier is not found!"));
        }
    }
}
