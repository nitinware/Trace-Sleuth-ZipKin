package com.example;

import java.util.*;

import org.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.sleuth.*;
import org.springframework.cloud.sleuth.sampler.*;
import org.springframework.context.annotation.*;
import org.springframework.http.client.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.*;

@SpringBootApplication
@RestController
public class Service4Application {

	private static final Logger log = LoggerFactory.getLogger(Service4Application.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}

	public static void main(String[] args) {
		SpringApplication.run(Service4Application.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		//interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@RequestMapping("/")
	public String root() {
		String msg = "Service 4 : Root";
		log.info(msg);
		return msg;
	}
}
