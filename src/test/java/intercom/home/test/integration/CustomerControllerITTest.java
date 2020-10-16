package intercom.home.test.integration;

import intercom.home.test.controller.CustomerController;
import intercom.home.test.service.CustomerService;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class CustomerControllerITTest {
  @InjectMocks CustomerController customerController;
  @Mock CustomerService customerService;
}
