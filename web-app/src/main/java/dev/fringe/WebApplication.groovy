package dev.fringe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import

import dev.fringe.config.MainConfig
import groovy.transform.Immutable

@SpringBootApplication
@ComponentScan("dev.fringe.controller")
@Import(value = MainConfig.class)
class WebApplication {

	static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args)
	}
}
