package com.app.API.currency;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class CurrencyController {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private ObjectMapper objectMapper;

    @RequestMapping(path = "api/public/currencies", method = RequestMethod.GET)
    public @ResponseBody List<Currency> getAll() { return currencyService.getAll(); }

    @RequestMapping(path = "api/public/admin/currencies", method = RequestMethod.POST)
    public @ResponseBody ObjectNode addCurrency(@RequestBody ObjectNode json) {
        ObjectNode objectNode = objectMapper.createObjectNode();
        try {
            currencyService.addCurrency(json.get("name").asText(), json.get("code").asText(), json.get("flow").asDouble(1f));
            objectNode.put("status", "success");
        } catch (Exception e) {
            objectNode.put("status", "failed");
        }
        return objectNode;
    }

}
