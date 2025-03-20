package ru.javabegin.micro.demo.countryidentification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javabegin.micro.demo.countryidentification.model.ISDCode;
import ru.javabegin.micro.demo.countryidentification.parser.ISDCodeParser;
import ru.javabegin.micro.demo.countryidentification.repository.ISDCodeRepository;
import ru.javabegin.micro.demo.countryidentification.util.ISDCodesTrie;

import java.util.List;

@Service
public class ISDCodeService {

    @Value("${country.codes.url}")
    private String codesUrl;
    @Value("${country.codes.table}")
    private String codesTable;

    private final ISDCodeParser parser;
    private final ISDCodeRepository repository;
    private final ISDCodesTrie codeTrie;

    public ISDCodeService(ISDCodeParser parser, ISDCodeRepository repository, ISDCodesTrie codeTrie) {
        this.parser = parser;
        this.repository = repository;
        this.codeTrie = codeTrie;
    }

    @EventListener(ApplicationReadyEvent.class)
    @Transactional
    public void loadISDCodes() {
        List<ISDCode> isdCodes = parser.parse(codesUrl, codesTable);
        repository.saveAll(isdCodes);
    }

    @Transactional
    public void createPrefixTrie() {
        List<ISDCode> all = repository.findAll();
        for (ISDCode code : all) {
            if (code.getAreaCodes().isEmpty()) {
                codeTrie.insert(code.getCountryCode(), "", code.getCountryName());
            } else {
                for (String areaCode : code.getAreaCodes()) {
                    codeTrie.insert(code.getCountryCode(), areaCode, code.getCountryName());
                }
            }
        }
    }

    public String getCountryByPhoneNumber(String number) {
        return codeTrie.findCountry(number);
    }
}