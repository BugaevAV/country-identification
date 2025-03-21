package ru.javabegin.micro.demo.countryidentification.configuration;

import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import ru.javabegin.micro.demo.countryidentification.service.ISDCodeService;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public ISDCodeService isdCodeService() {
        return Mockito.mock(ISDCodeService.class);
    }
}