package uz.pcmarket.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pcmarket.pcmarket.entity.Address;
@Repository
public interface AddressRepository extends JpaRepository<Address,Integer> {
}
