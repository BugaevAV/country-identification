package ru.javabegin.micro.demo.countryidentification.parser;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import ru.javabegin.micro.demo.countryidentification.model.ISDCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ISDCodeParserTest {

    @Test
    void testParseRow() {
        ISDCodeParser parser = new ISDCodeParser();
        List<ISDCode> isdCodes = new ArrayList<>();

        Element column1 = mock(Element.class);
        Element column2 = mock(Element.class);

        when(column1.text()).thenReturn("Kazakhstan");
        when(column2.text()).thenReturn("7 (6, 7) (997 assigned but now abandoned)");

        Elements columns = new Elements(Arrays.asList(column1, column2));

        parser.parseRow(columns, isdCodes);

        assertEquals(1, isdCodes.size());
        ISDCode isdCode = isdCodes.get(0);
        assertEquals("Kazakhstan", isdCode.getCountryName());
        assertEquals("7", isdCode.getCountryCode());
        assertEquals(Arrays.asList("6", "7", "997"), isdCode.getAreaCodes());
    }

    @Test
    void testGetElement() {
        ISDCodeParser parser = new ISDCodeParser();

        Element targetHeader = mock(Element.class);
        Element parent = mock(Element.class);
        Element nextElement = mock(Element.class);
        Element tableElement = mock(Element.class);

        when(targetHeader.parent()).thenReturn(parent);
        when(parent.nextElementSibling()).thenReturn(nextElement);
        when(nextElement.tagName()).thenReturn("div");
        when(nextElement.nextElementSibling()).thenReturn(tableElement);
        when(tableElement.tagName()).thenReturn("table");

        Element result = parser.getElement(targetHeader);

        assertNotNull(result);
        assertEquals("table", result.tagName());
    }
}