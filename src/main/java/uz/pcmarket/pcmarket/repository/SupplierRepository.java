package uz.pcmarket.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pcmarket.pcmarket.entity.Supplier;

import javax.validation.constraints.NotNull;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
