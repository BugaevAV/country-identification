package ru.javabegin.micro.demo.countryidentification.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import ru.javabegin.micro.demo.countryidentification.service.ISDCodeService;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class PhoneControllerTest {

    @Mock
    private ISDCodeService isdCodeService;

    @InjectMocks
    private PhoneController phoneController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCountryByPhone_ValidNumber() {
        String number = "11165384765";
        String country = "Canada, United States";
        when(isdCodeService.getCountryByPhoneNumber(number)).thenReturn(country);

        ResponseEntity<?> response = phoneController.getCountryByPhone(number);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(Map.of("country", "Результат поиска: " + country), response.getBody());
    }

    @Test
    void testGetCountryByPhone_invalidNumber() {
        String number = "5990000000";
        when(isdCodeService.getCountryByPhoneNumber(number)).thenReturn(null);

        ResponseEntity<?> response = phoneController.getCountryByPhone(number);

        assertEquals(200, response.getStatusCode().value());
        assertEquals(Map.of("country", "Страна с таким кодом не найдена!"), response.getBody());
    }
}