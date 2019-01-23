package study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import study.Beans.MyBean;
import study.JPA.Customer;
import study.JPA.CustomerRepository;
import study.config.TypeSafeConfiguration;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;


//@EnableAutoConfiguration
@SpringBootApplication    // same as @Configuration @EnableAutoConfiguration @ComponentScan
@RestController           // tells our class is a web @Controller, so Spring considers it when handling incoming web requests. + 
                          // @RestController annotation tells Spring to render the resulting string directly back to the caller.
@EnableConfigurationProperties({
								TypeSafeConfiguration.class
							  })    // When using configuration classes, you also need to list the properties
                                    // classes to register in the @EnableConfigurationProperties annotation
public class StudyApplication {
	
	@Autowired
	MyBean mybean;
	
	@Autowired
	TypeSafeConfiguration typeSafeConfiguration;
	
	private static final Logger log = LoggerFactory.getLogger(StudyApplication.class);
	
	@RequestMapping("/config_and_bean")    // our class is a web @Controller, so Spring considers it when handling incoming web requests.
	String home() {		
		return "Hello " + mybean.name;
//		return "typesave config: " + typeSafeConfiguration.getSecurity().getUsername();
	}

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);    // Spring Boot’s SpringApplication.run() method to launch an application
	}
	
	@Bean
	public CommandLineRunner demo(CustomerRepository repository) {
		return (args) -> {
			// save a couple of customers
			repository.save(new Customer("Jack", "Bauer"));
			repository.save(new Customer("Chloe", "O'Brian"));
			repository.save(new Customer("Kim", "Bauer"));
			repository.save(new Customer("David", "Palmer"));
			repository.save(new Customer("Michelle", "Dessler"));

			// fetch all customers
			log.info("Customers found with findAll():");
			log.info("-------------------------------");
			for (Customer customer : repository.findAll()) {
				log.info(customer.toString());
			}
			log.info("");

			// fetch an individual customer by ID
			repository.findById(1L)
				.ifPresent(customer -> {
					log.info("Customer found with findById(1L):");
					log.info("--------------------------------");
					log.info(customer.toString());
					log.info("");
				});

			// fetch customers by last name
			log.info("Customer found with findByLastName('Bauer'):");
			log.info("--------------------------------------------");
			repository.findByLastName("Bauer").forEach(bauer -> {
				log.info(bauer.toString());
			});
			// for (Customer bauer : repository.findByLastName("Bauer")) {
			// 	log.info(bauer.toString());
			// }
			log.info("");
		};
	}


}

