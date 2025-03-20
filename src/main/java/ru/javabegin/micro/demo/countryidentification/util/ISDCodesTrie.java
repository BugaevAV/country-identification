package ru.javabegin.micro.demo.countryidentification.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

class TrieNode {
    Map<Character, TrieNode> children = new HashMap<>();
    String countryName;
}

@Component
public class ISDCodesTrie {

    private final TrieNode root = new TrieNode();

    public void insert(String code, String areaCode, String countryName) {
        TrieNode current = root;
        String fullCode = code + areaCode;

        for (char ch : fullCode.toCharArray()) {
            current = current.children.computeIfAbsent(ch, c -> new TrieNode());
        }

        if (current.countryName != null && !current.countryName.contains(countryName)) {
            current.countryName = current.countryName + ", " + countryName;
        } else {
            current.countryName = countryName;
        }

    }

    public String findCountry(String number) {
        if (number == null) {
            return null;
        }

        TrieNode current = root;
        String foundCountry = null;

        for (char ch : number.toCharArray()) {
            if (!current.children.containsKey(ch)) {
                break;
            }
            current = current.children.get(ch);
            if (current.countryName != null) {
                foundCountry = current.countryName;
            }
        }
        return foundCountry;
    }
}