package ru.javabegin.micro.demo.countryidentification.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javabegin.micro.demo.countryidentification.service.ISDCodeService;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class PhoneController {

    private final ISDCodeService codeService;

    public PhoneController(ISDCodeService codeService) {
        this.codeService = codeService;
    }

    @GetMapping("/phone/{number}")
    public ResponseEntity<?> getCountryByPhone(@PathVariable String number) {
        String country = codeService.getCountryByPhoneNumber(number);
        if (country == null) {
            return ResponseEntity.ok(Map.of("country","Страна с таким кодом не найдена!"));
        }
        return ResponseEntity.ok(Map.of("country", "Результат поиска: " + country));
    }
}
