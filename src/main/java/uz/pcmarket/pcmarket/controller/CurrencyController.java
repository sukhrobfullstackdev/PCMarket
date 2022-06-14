package uz.pcmarket.pcmarket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pcmarket.pcmarket.entity.Currency;
import uz.pcmarket.pcmarket.payload.Message;
import uz.pcmarket.pcmarket.service.CurrencyService;

@RestController
@RequestMapping(value = "/currency")
public class CurrencyController {
    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @PreAuthorize(value = "hasAuthority('READ_ALL_PRODUCT')")
    @GetMapping
    public ResponseEntity<Page<Currency>> getCurrencies(@RequestParam int page, @RequestParam int size) {
        return currencyService.getCurrenciesService(page, size);
    }

    @PreAuthorize(value = "hasAuthority('READ_PRODUCT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<Currency> getCurrency(@PathVariable Integer id) {
        return currencyService.getCurrencyService(id);
    }

    @PreAuthorize(value = "hasAuthority('ADD_PRODUCT')")
    @PostMapping
    public ResponseEntity<Message> addCurrency(@RequestBody Currency currency) {
        return currencyService.addCurrencyService(currency);
    }

    @PreAuthorize(value = "hasAuthority('EDIT_PRODUCT')")
    @PutMapping(value = "/{id}")
    public ResponseEntity<Message> editCurrency(@PathVariable Integer id, @RequestBody Currency currency) {
        return currencyService.editCurrencyService(id, currency);
    }

    @PreAuthorize(value = "hasAuthority('DELETE_PRODUCT')")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Message> deleteCurrency(@PathVariable Integer id) {
        return currencyService.deleteCurrencyService(id);
    }
}
