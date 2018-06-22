package rsvier.workshop;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(basePackages={"rsvier.workshop","rsvier.workshop.controller","rsvier.workshop.dao","rsvier.workshop.service","rsvier.workshop.view"})

public class ApplicationConfig {

}


/*
This class will scan all component from controller, dao , service and view packages.

*/