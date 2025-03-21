package ru.javabegin.micro.demo.countryidentification.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IndexPageControllerTest {

    @Test
    void testIndexPage() {

        IndexPageController controller = new IndexPageController();

        String viewName = controller.indexPage();

        assertEquals("index", viewName);
    }
}