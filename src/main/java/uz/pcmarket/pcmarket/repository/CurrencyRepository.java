package uz.pcmarket.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pcmarket.pcmarket.entity.Currency;

@Repository
public interface CurrencyRepository extends JpaRepository<Currency,Integer> {
    boolean existsByName(String name);
    boolean existsByNameAndIdNot(String name, Integer id);
}
