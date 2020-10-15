package intercom.home.test.controller;

import intercom.home.test.model.CustomerResponse;
import intercom.home.test.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping("/")
    public List<CustomerResponse> getCustomersNearDublin() throws IOException {
        try {
            return customerService.getCustomersNearDublin();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

}
