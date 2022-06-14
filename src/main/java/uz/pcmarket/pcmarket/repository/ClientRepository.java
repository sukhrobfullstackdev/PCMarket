package uz.pcmarket.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pcmarket.pcmarket.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client,Integer> {
    boolean existsByName(String name);
}
