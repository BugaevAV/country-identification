package ru.javabegin.micro.demo.countryidentification.model;

import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "country_codes")
public class ISDCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "country_name")
    private String countryName;
    @Column(name = "country_code")
    private String countryCode;
    @ElementCollection
    @CollectionTable(name = "area_codes", joinColumns = @JoinColumn(name = "isd_code_id"))
    @Column(name = "area_code")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<String> areaCodes = new ArrayList<>();

    public ISDCode(String countryName, String countryCode, List<String> areaCodes) {
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.areaCodes = areaCodes;
    }

    public ISDCode() {

    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String code) {
        this.countryCode = code;
    }

    public List<String> getAreaCodes() {
        return areaCodes;
    }

    public void setAreaCodes(List<String> areaCodes) {
        this.areaCodes = areaCodes;
    }
}