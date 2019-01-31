package study;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import study.JPA.Customer;
import study.JPA.CustomerRepository;
import study.JSON.Car;
import study.JSON.Post;
import study.algorithm.ReverseInteger;
import study.algorithm.TwoSum;
import study.beans.MyBean;
import study.config.TypeSafeConfiguration;

import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.hibernate.mapping.Array;
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

	public static void main(String[] args) throws JsonParseException, JsonMappingException, IOException {
//		System.out.println(ReverseInteger.reverse(-123));
		
//		int[] result = TwoSum.twoSum(new int[] {15, 7, 11, 2}, 9);
//		System.out.println(result[0] + "," + result[1]);
		
		
		String jsonPostArray = "[{\"id\":14186,\"title\":\"列治文宁静宜两房一厅3张床独立套间\",\"description\":\"第一次出租：里士满大别别墅二楼高雅阁二号路安静内街独立卫浴新家工具电话：604-780-3826\"}]";
//		String jsonCarArray = "[{ \"color\" : \"Black\", \"type\" : \"BMW\" }, { \"color\" : \"Red\", \"type\" : \"FIAT\" }]";
		ObjectMapper objectMapper = new ObjectMapper();
//		List<Car> listCar = objectMapper.readValue(jsonCarArray, new TypeReference<List<Car>>(){});
		List<Post> listCar = objectMapper.readValue(jsonPostArray, new TypeReference<List<Post>>(){});
		System.out.println(listCar);
		
		System.out.println("================");
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
//		SpringApplication.run(StudyApplication.class, args);    // Spring Boot’s SpringApplication.run() method to launch an application
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

