package intercom.home.test.service;

import intercom.home.test.model.CustomerResponse;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    List<CustomerResponse> getCustomersNearDublin() throws IOException;
}
