package spring.k8s.helloclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
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

	@Autowired
	RestTemplate restTemplate;

	@GetMapping("/{name}")
	public String callHello(@PathVariable String name) {
		System.out.println("Calling hello-service");
		String str = restTemplate.getForObject("http://hello-service/" + name, String.class);
		System.out.println("Return: " + str);
		return "hello-client says " + str;
	}

	@GetMapping("/")
	public String sayHi() {
		return "Hi from hello-client!";
	}
}
