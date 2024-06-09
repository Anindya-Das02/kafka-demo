package in.das.app.kafka.functional;

import in.das.app.kafka.service.KafkaTestService;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Slf4j
public class StepsDefinition {

    private final RestTemplate restTemplate = new RestTemplate();

    private final KafkaTestService kafkaTestService = new KafkaTestService();

    private final String baseUrl = "http://localhost:8085/kafka";

    private ResponseEntity<?> responseEntity = null;

    private final String message = "{\"data\":\"reading from kafka\"}";

    @When("test api is hit")
    public void callApi(){
        responseEntity = restTemplate.exchange(
                baseUrl + "/test",
                HttpMethod.GET,
                null,
                String.class
        );
    }

    @When("publish api is hit")
    public void callPublishApi(){
        // Set up the headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // Set the Content-Type header


        // Create an HttpEntity containing the payload and headers
        HttpEntity<Object> requestEntity = new HttpEntity<>(Map.of("data","reading from kafka"), headers);

        // Send the POST request and get the response
        responseEntity = restTemplate.exchange(baseUrl + "/publish", HttpMethod.POST, requestEntity, String.class);

    }

    @Then("response status should be {int}")
    public void checkResponseStatus(int statusCode){
        assertEquals(statusCode, responseEntity.getStatusCode().value());
    }

    @And("see {string} as response")
    public void checkResponseMessage(String message){
        assertEquals(message, responseEntity.getBody());
    }

    @And("verify published kafka message")
    public void verifyKafkaMessage(){
        List<String> messages = kafkaTestService.getMessages();
        String consumedMessage = messages.get(messages.size() - 1);
        assertEquals(message, consumedMessage);
    }

    @Then("read all kafka messages")
    public void readAllMessages(){
        List<String> messages = kafkaTestService.getMessages();
        messages.forEach(System.out::println);
    }
}
