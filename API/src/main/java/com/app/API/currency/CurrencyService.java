package com.app.API.currency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    public List<Currency> getAll() {
        return currencyRepository.findAll();
    }

    public void addCurrency(String name, String code, Double flow)  {
        Currency new_currency = new Currency((long) 0, name, code, flow.floatValue());
        currencyRepository.save(new_currency);
    }
}
