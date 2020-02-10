package com.app.Services;

import org.springframework.stereotype.Component;

@Component
public class ExempluServiciu implements Interfata_Serviciu {
    @Override
    public String write(String text) {
        return text + " a fost scris cu talent";
    }
}
