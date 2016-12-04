package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import zipkin.server.*;

@SpringBootApplication
@EnableZipkinServer
public class ZipkincassandraApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZipkincassandraApplication.class, args);
	}
}
