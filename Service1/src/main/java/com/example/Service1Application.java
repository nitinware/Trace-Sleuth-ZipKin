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
public class Service1Application {

	private static final Logger log = LoggerFactory.getLogger(Service1Application.class.getName());

	@Autowired
	private RestTemplate restTemplate;

	public static void main(String[] args) {
		SpringApplication.run(Service1Application.class, args);
	}

	@Bean
	public RestTemplate getRestTemplate() {
		RestTemplate restTemplate = new RestTemplate(new BufferingClientHttpRequestFactory(new SimpleClientHttpRequestFactory()));
		List<ClientHttpRequestInterceptor> interceptors = new ArrayList<ClientHttpRequestInterceptor>();
		//interceptors.add(new LoggingRequestInterceptor());
		restTemplate.setInterceptors(interceptors);
		return restTemplate;
	}

	@Bean
	public Sampler defaultSampler() {
		return new AlwaysSampler();
	}


	@RequestMapping("/")
	public String root() {
		String msg = "Service 1 : Root";
		log.info(msg);
		return msg;
	}

	@RequestMapping("/callService2")
	public String callService2() {
		String msg = "service1 : callService2";
		msg += " --> " + restTemplate.getForObject("http://localhost:9090", String.class);
		log.info(msg);
		return msg;
	}

	@RequestMapping("/callService4")
	public String callService4() {
		String msg = "service1 : callService4";
		msg += " --> " + restTemplate.getForObject("http://localhost:5050", String.class);
		log.info(msg);
		return msg;
	}

}
