package spring.k8s.helloclient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.PathVariable;

@SpringBootApplication
public class HelloClientApplication {

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

	public static void main(String[] args) {
		SpringApplication.run(HelloClientApplication.class, args);
	}

}

@RestController
class HelloClientController {

	private final HelloService helloService;

	public HelloClientController(HelloService helloService) {
		this.helloService = helloService;
	}

	@GetMapping("/{name}")
	public String callHello(@PathVariable String name) {
		return helloService.getHello(name);
	}

	@GetMapping("/")
	public String sayHi() {
		return "Hi from hello-client!";
	}
}

@Service
class HelloService {

	private final RestTemplate restTemplate;

	@Value("${helloServiceURL:http://hello-service/}")
	String helloServiceURL;

	public HelloService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	public String getHello(String name) {
		System.out.println("Calling hello-service");
		String str = restTemplate.getForObject(helloServiceURL + name, String.class);
		System.out.println("Return: " + str);
		return "hello-client says " + str;
	}

	// for tests
	void setHelloServiceURL(String url) {
		this.helloServiceURL = url;
	}

}