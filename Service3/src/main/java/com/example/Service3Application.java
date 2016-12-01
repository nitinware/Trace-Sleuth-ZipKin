package com.example;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.*;
import org.springframework.context.annotation.*;
import org.springframework.http.client.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.cloud.sleuth.sampler.*;
import org.springframework.web.client.*;

@SpringBootApplication
@RestController
public class Service3Application {

	private static final Logger log = LoggerFactory.getLogger(Service3Application.class.getName());

	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		//interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	public static void main(String[] args) {
		SpringApplication.run(Service3Application.class, args);
	}

	@RequestMapping("/")
	public String root() {
		String msg = "Service 3 : Root";
		log.info(msg);
		return msg;
	}

}
