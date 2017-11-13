package fr.emse.majeureinfo.springbootintro;


import fr.emse.majeureinfo.springbootintro.hello.ConsoleGreetingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication public class SpringBootIntroApplication {

	private ConsoleGreetingService consoleGreetingService;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootIntroApplication.class, args);
	}

}
