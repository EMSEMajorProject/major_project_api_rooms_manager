package fr.emse.majeureinfo.springbootintro.hello;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


@Component
public class ConsoleGreetingService implements GreetingService{

    @Override
    public void greet (String name){
        System.out.println("Hello, "+name+"!");
    }
}

