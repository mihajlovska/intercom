package intercom.home.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import intercom.home.test.model.CustomerResponse;

import java.util.List;

public interface CustomerService {
  List<CustomerResponse> getCustomersNearDublin(String bucketName, String keyName)
          throws JsonProcessingException, NoSuchFieldException;
}
