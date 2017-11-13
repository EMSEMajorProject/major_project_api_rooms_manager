package fr.emse.majeureinfo.springbootintro.hello;

import org.springframework.stereotype.Component;

@Component
public interface GreetingService {

    void greet(String name);
}