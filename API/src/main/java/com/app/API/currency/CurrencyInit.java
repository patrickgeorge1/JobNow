package com.app.API.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
@Order(value = 4)
public class CurrencyInit implements CommandLineRunner {
    @Autowired
    private CurrencyRepository currencyRepository;

    @Override
    public void run(String... args) throws Exception {
        currencyRepository.deleteAll();
        Currency ron = new Currency((long) 0, "leu romanesc", "RON", 1f);
        Currency euro = new Currency((long) 0, "euro", "EUR", 4.5f);
        Currency dolar = new Currency((long) 0, "dolar american", "USD", 4.3f);

        currencyRepository.saveAll( Arrays.asList(ron, euro, dolar));
    }
}
