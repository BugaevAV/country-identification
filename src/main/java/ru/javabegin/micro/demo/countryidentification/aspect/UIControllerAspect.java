package ru.javabegin.micro.demo.countryidentification.aspect;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import ru.javabegin.micro.demo.countryidentification.service.ISDCodeService;

@Aspect
@Component
public class UIControllerAspect {

    private final ISDCodeService service;

    public UIControllerAspect(ISDCodeService service) {
        this.service = service;
    }

    @After("execution(* ru.javabegin.micro.demo.countryidentification" +
           ".controller.IndexPageController.indexPage(..))")
    public void afterControllerMethod() {
        service.createPrefixTrie();
    }
}