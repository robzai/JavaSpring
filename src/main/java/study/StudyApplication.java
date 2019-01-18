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
import study.config.TypeSafeConfiguration;


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
	
	@RequestMapping("/")    // our class is a web @Controller, so Spring considers it when handling incoming web requests.
	String home() {		
		return "Hello " + mybean.name;
//		return "typesave config: " + typeSafeConfiguration.getSecurity().getUsername();
	}

	public static void main(String[] args) {
		SpringApplication.run(StudyApplication.class, args);
	}

}

