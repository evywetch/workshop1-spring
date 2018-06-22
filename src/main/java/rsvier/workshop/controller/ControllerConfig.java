package rsvier.workshop.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"rsvier.workshop.controller","rsvier.workshop.dao","rsvier.workshop.service","rsvier.workshop.view"})
public class ControllerConfig {

}


/*

This config class gather all other config class from other pakages, then u can pass only this class
in AnnotationConfigApplicationContext then it will store all the beans from other pakage too.


*/