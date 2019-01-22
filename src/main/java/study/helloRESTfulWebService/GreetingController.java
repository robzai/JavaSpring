package study.helloRESTfulWebService;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController    // @RestController marks the class as a controller where every method returns a domain object instead of a view.
				   // It’s shorthand for @Controller and @ResponseBody rolled together.
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    // handles GET requests for /greeting by returning a new instance of the Greeting class
    @RequestMapping("/greeting")    // The @RequestMapping annotation ensures that HTTP requests to /greeting are mapped to the greeting() method.
    								// The above example does not specify GET vs. PUT, POST, because @RequestMapping maps all HTTP operations by default.
    								// Use @RequestMapping(method=GET) to narrow this mapping.
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {    // @RequestParam binds the value of the url parameter 
    																							 // name into the name parameter of the greeting() method. 
    																							 // If the name parameter is absent in the request, the defaultValue of "World" is used.
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}