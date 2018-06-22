package rsvier.workshop.controller;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages={"rsvier.workshop.controller","rsvier.workshop.dao","rsvier.workshop.service","rsvier.workshop.view"})
public class ControllerConfig {

}
