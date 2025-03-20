package ru.javabegin.micro.demo.countryidentification.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.javabegin.micro.demo.countryidentification.model.ISDCode;

public interface ISDCodeRepository extends JpaRepository<ISDCode, Long> {
}
