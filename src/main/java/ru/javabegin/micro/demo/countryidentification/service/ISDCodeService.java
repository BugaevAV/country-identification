package ru.javabegin.micro.demo.countryidentification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.javabegin.micro.demo.countryidentification.model.ISDCode;
import ru.javabegin.micro.demo.countryidentification.parser.ISDCodeParser;
import ru.javabegin.micro.demo.countryidentification.repository.ISDCodeRepository;

import java.io.IOException;
import java.util.List;

@Service
public class ISDCodeService {

    @Value("${country.codes.url}")
    private String codesUrl;
    @Value("${country.codes.table}")
    private String codesTable;

    private final ISDCodeParser parser;
    private final ISDCodeRepository repository;

    public ISDCodeService(ISDCodeParser parser, ISDCodeRepository repository) {
        this.parser = parser;
        this.repository = repository;
    }

    public void loadISDCodes() throws IOException {
        List<ISDCode> isdCodes = parser.parse(codesUrl, codesTable);
        repository.saveAll(isdCodes);
    }
}
