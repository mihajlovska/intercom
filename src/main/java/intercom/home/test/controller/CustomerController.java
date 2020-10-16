package intercom.home.test.controller;

import intercom.home.test.model.CustomerResponse;
import intercom.home.test.service.CustomerService;
import intercom.home.test.utils.properties.AmazonS3Properties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {
  @Autowired CustomerService customerService;
  @Autowired AmazonS3Properties amazonS3Properties;

  @GetMapping("/")
  public List<CustomerResponse> getCustomersNearDublin() throws IOException {
    try {
      return customerService.getCustomersNearDublin(
          amazonS3Properties.getBucketName(), amazonS3Properties.getKeyName());
    } catch (IOException e) {
      throw new IOException(e.getMessage());
    }
  }
}
