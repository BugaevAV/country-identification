package ru.javabegin.micro.demo.countryidentification.aspect;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.javabegin.micro.demo.countryidentification.service.ISDCodeService;

@ExtendWith(MockitoExtension.class)
class UIControllerAspectTest {

    @Mock
    private ISDCodeService service;

    @InjectMocks
    private UIControllerAspect aspect;

    @Test
    void testAfterControllerMethod() {
        aspect.afterControllerMethod();

        Mockito.verify(service, Mockito.times(1)).createPrefixTrie();
    }
}