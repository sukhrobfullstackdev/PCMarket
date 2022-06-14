package uz.pcmarket.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pcmarket.pcmarket.entity.Computer;

@Repository
public interface ComputerRepository extends JpaRepository<Computer,Integer> {
}
