package ru.javabegin.micro.demo.countryidentification.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import ru.javabegin.micro.demo.countryidentification.configuration.TestConfig;
import ru.javabegin.micro.demo.countryidentification.service.ISDCodeService;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(TestConfig.class)
class PhoneControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ISDCodeService isdCodeService;

    @Test
    void testGetCountryByPhone_ValidNumber() throws Exception {
        String number = "11165384765";
        String country = "Canada, United States";
        when(isdCodeService.getCountryByPhoneNumber(number)).thenReturn(country);

        mockMvc.perform(get("/api/v1/phone/{number}", number))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("Результат поиска: " + country));
    }

    @Test
    void testGetCountryByPhone_InvalidNumber() throws Exception {
        String number = "5990000000";
        when(isdCodeService.getCountryByPhoneNumber(number)).thenReturn(null);

        mockMvc.perform(get("/api/v1/phone/{number}", number))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.country").value("Страна с таким кодом не найдена!"));
    }

}