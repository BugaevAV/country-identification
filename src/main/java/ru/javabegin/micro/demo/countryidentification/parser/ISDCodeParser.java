package ru.javabegin.micro.demo.countryidentification.parser;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;
import ru.javabegin.micro.demo.countryidentification.model.ISDCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class ISDCodeParser implements Parser {

    @Override
    public List<ISDCode> parse(String url, String tableName) {
        List<ISDCode> isdCodes = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Element targetHeader = doc.select("h2:contains(" + tableName + ")").first();

            if (targetHeader == null) {
                throw new IllegalArgumentException("Заголовок таблицы не найден: " + tableName);
            }

            Element tableElement = getElement(targetHeader);
            if (tableElement == null) {
                throw new IllegalArgumentException("Таблица не найдена: " + tableName);
            }

            Elements rows = tableElement.select("tr");
            for (Element row : rows) {
                Elements columns = row.select("td");
                parseRow(columns, isdCodes);
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при загрузке HTML-документа: " + url, e);
        }
        return isdCodes;
    }

    private void parseRow(Elements columns, List<ISDCode> isdCodes) {
        if (columns.size() >= 2) {
            String countryName = columns.get(0).text();
            String code = columns.get(1).text();
            int spaceIndex = code.indexOf(" ");

            String countryCode = code;
            List<String> areaCodes = Collections.emptyList();

            if (spaceIndex != -1) {
                countryCode = code.substring(0, spaceIndex);
                areaCodes = Arrays.asList(code.substring(spaceIndex + 1)
                        .replaceAll("[^\\d ]", "")
                        .split(" "));
            }

            ISDCode isdCode = new ISDCode(countryName, countryCode, areaCodes);
            isdCodes.add(isdCode);
        }
    }

    private Element getElement(Element targetHeader) {
        Element nextElement = targetHeader.parent().nextElementSibling();
        while (nextElement != null && !nextElement.tagName().equals("table")) {
            nextElement = nextElement.nextElementSibling();
        }
        return nextElement;
    }
}