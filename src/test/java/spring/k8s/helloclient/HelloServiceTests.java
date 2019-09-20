package spring.k8s.helloclient;

import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HelloClientApplication.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
@AutoConfigureStubRunner
public class HelloServiceTests {

    HelloService helloService = new HelloService(new RestTemplate());

    @Test
    public void shouldSendRockstarNameToHelloService() {

        // Expects system property stubrunner.ids to be set with a single local stub.
        //    e.g. use mvn with:
        //   -Dstubrunner.ids=com.example:simple-producer:build-1:stubs:10000
        //   -Dstubrunner.stubs-mode=LOCAL

        String name = "Rockstar";
        helloService.setHelloServiceURL("http://localhost:10000");
        // when
        String greeting = helloService.getHello(name);
        // then
        BDDAssertions.then(greeting).isEqualTo("Hello " + name + "!");
    }

}