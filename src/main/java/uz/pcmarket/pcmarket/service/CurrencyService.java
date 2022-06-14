package uz.pcmarket.pcmarket.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.pcmarket.pcmarket.entity.Currency;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.repository.CurrencyRepository;

import java.util.Objects;
import java.util.Optional;

@Service
public class CurrencyService {
    final CurrencyRepository currencyRepository;

    public CurrencyService(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }

    public ResponseEntity<Page<Currency>> getCurrenciesService(int page, int size) {
        return ResponseEntity.status(HttpStatus.OK).body(currencyRepository.findAll(PageRequest.of(page, size)));
    }

    public ResponseEntity<Currency> getCurrencyService(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        return optionalCurrency.map(currency -> ResponseEntity.status(HttpStatus.OK).body(currency)).orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    public ResponseEntity<Message> addCurrencyService(Currency currency) {
        if (!Objects.equals(currency.getName(), "")) {
            boolean exists = currencyRepository.existsByName(currency.getName());
            if (!exists) {
                currencyRepository.save(currency);
                return ResponseEntity.status(HttpStatus.CREATED).body(new Message(true, "The currency has been successfully added!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The currency is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "Please enter name fully!"));
        }
    }

    public ResponseEntity<Message> editCurrencyService(Integer id, Currency currency) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            if (!currencyRepository.existsByNameAndIdNot(currency.getName(), id)) {
                Currency editingCurrency = optionalCurrency.get();
                editingCurrency.setName(currency.getName());
                editingCurrency.setActive(currency.isActive());
                currencyRepository.save(editingCurrency);
                return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message(true, "The currency has been successfully edited!"));
            } else {
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(new Message(false, "The currency is already exists!"));
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, ""));
        }
    }

    public ResponseEntity<Message> deleteCurrencyService(Integer id) {
        Optional<Currency> optionalCurrency = currencyRepository.findById(id);
        if (optionalCurrency.isPresent()) {
            currencyRepository.delete(optionalCurrency.get());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new Message(true, "The currency was successfully deleted!"));
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Message(false, "The currency that you want to delete was not found!"));
        }
    }
}
