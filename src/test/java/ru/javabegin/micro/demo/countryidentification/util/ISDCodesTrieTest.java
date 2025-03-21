package ru.javabegin.micro.demo.countryidentification.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ISDCodesTrieTest {

    private ISDCodesTrie isdCodesTrie;

    @BeforeEach
    void setUp() {
        isdCodesTrie = new ISDCodesTrie();
    }

    @Test
    void testInsertAndFindCountry() {
        isdCodesTrie.insert("1", "234", "United States");
        isdCodesTrie.insert("1", "235", "Canada");
        isdCodesTrie.insert("44", "20", "United Kingdom");

        assertEquals("United States", isdCodesTrie.findCountry("1234"));
        assertEquals("Canada", isdCodesTrie.findCountry("1235"));
        assertEquals("United Kingdom", isdCodesTrie.findCountry("4420"));
        assertNull(isdCodesTrie.findCountry("5990"));
    }

    @Test
    void testInsertDuplicateCountry() {
        isdCodesTrie.insert("1", "234", "United States");
        isdCodesTrie.insert("1", "234", "USA");

        assertEquals("United States, USA", isdCodesTrie.findCountry("1234"));
    }

    @Test
    void testFindCountryWithPartialMatch() {
        isdCodesTrie.insert("1", "234", "United States");
        isdCodesTrie.insert("1", "235", "Canada");

        assertEquals("United States", isdCodesTrie.findCountry("1234"));
        assertEquals("Canada", isdCodesTrie.findCountry("1235"));
        assertNull(isdCodesTrie.findCountry("12"));
    }

    @Test
    void testFindCountryWithNullInput() {
        assertNull(isdCodesTrie.findCountry(null));
    }

    @Test
    void testInsertWithEmptyCode() {
        isdCodesTrie.insert("", "", "Unknown");

        assertNull(isdCodesTrie.findCountry(""));
    }

    @Test
    void testFindCountryWithEmptyInput() {
        assertNull(isdCodesTrie.findCountry(""));
    }
}