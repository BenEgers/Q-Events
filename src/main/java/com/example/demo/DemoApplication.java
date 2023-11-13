package com.example.demo;

import com.example.demo.view.Menu;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class DemoApplication {
	private final Menu menu;

	//Don't have to instantiate an object of Menu,
	// The Application context makes an object, because of the @Component annotation
	public DemoApplication(Menu menu) {
		this.menu = menu;
	}

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	//Runs after the Springboot application has loaded.
	@EventListener(ApplicationReadyEvent.class)
	public void start(){
		menu.startMenu();
	}
}
