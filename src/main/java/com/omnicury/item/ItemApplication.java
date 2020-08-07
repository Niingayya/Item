package com.omnicury.item;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.omnicury.item.model.Item;

@SpringBootApplication
public class ItemApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(ItemApplication.class, args);
	}

}
