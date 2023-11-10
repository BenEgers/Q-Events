package com.example.demo;

import com.example.demo.view.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {

	private final Menu menu;
	public DemoApplication(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {

		SpringApplication.run(DemoApplication.class, args);

	}

	@EventListener(ApplicationReadyEvent.class)
	public void start(){

		menu.startMenu();
	}

}
