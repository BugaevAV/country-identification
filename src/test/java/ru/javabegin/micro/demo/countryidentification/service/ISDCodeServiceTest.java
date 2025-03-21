package ru.javabegin.micro.demo.countryidentification.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;
import ru.javabegin.micro.demo.countryidentification.model.ISDCode;
import ru.javabegin.micro.demo.countryidentification.parser.ISDCodeParser;
import ru.javabegin.micro.demo.countryidentification.repository.ISDCodeRepository;
import ru.javabegin.micro.demo.countryidentification.util.ISDCodesTrie;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ISDCodeServiceTest {

    @Mock
    private ISDCodeParser parser;

    @Mock
    private ISDCodeRepository repository;

    @Mock
    private ISDCodesTrie codeTrie;

    @InjectMocks
    private ISDCodeService isdCodeService;

    @Test
    void testLoadISDCodes() {
        ReflectionTestUtils.setField(isdCodeService, "codesUrl", "http://example.com/codes");
        ReflectionTestUtils.setField(isdCodeService, "codesTable", "country_codes");

        List<ISDCode> isdCodes = Collections.singletonList(
                new ISDCode("United States", "1", Collections.emptyList()));
        when(parser.parse("http://example.com/codes", "country_codes")).thenReturn(isdCodes);

        isdCodeService.loadISDCodes();

        verify(repository, times(1)).saveAll(isdCodes);
    }

    @Test
    void testCreatePrefixTrie() {

        List<ISDCode> isdCodes = Collections.singletonList(
                new ISDCode("United States", "1", Collections.emptyList()));
        when(repository.findAll()).thenReturn(isdCodes);

        isdCodeService.createPrefixTrie();

        verify(codeTrie, times(1)).insert("1", "", "United States");
    }

    @Test
    void testGetCountryByPhoneNumber() {
        when(codeTrie.findCountry("77112227231")).thenReturn("Kazakhstan");

        String country = isdCodeService.getCountryByPhoneNumber("77112227231");

        assertEquals("Kazakhstan", country);
        verify(codeTrie, times(1)).findCountry("77112227231");
    }
}