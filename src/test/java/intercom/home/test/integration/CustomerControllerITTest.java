package intercom.home.test.integration;

import static intercom.home.test.common.TestConstants.BASE_URL;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
class CustomerControllerITTest {

  @Test
  void getCustomersNearDublinSuccessTest() {
    RestTemplate restTemplate = new RestTemplate();

    ResponseEntity<String> response =
        restTemplate.exchange(BASE_URL, HttpMethod.GET, new HttpEntity<>(null), String.class);

    String responseBody = response.getBody();

    assertEquals(HttpStatus.OK, response.getStatusCode());
    assertNotNull(responseBody);
  }
}
