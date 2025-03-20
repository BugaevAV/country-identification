package ru.javabegin.micro.demo.countryidentification.parser;

import ru.javabegin.micro.demo.countryidentification.model.ISDCode;

import java.io.IOException;
import java.util.List;

public interface Parser {
    List<ISDCode> parse(String url, String tableName) throws IOException;
}
