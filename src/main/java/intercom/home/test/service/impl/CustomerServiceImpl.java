package intercom.home.test.service.impl;

import com.amazonaws.services.s3.model.S3Object;
import com.fasterxml.jackson.databind.ObjectMapper;
import intercom.home.test.model.CustomerRequest;
import intercom.home.test.model.CustomerResponse;
import intercom.home.test.service.AmazonS3Service;
import intercom.home.test.service.CustomerService;
import intercom.home.test.service.DistanceCalculatorService;
import static intercom.home.test.utils.AmazonS3Constants.S3_BUCKET_NAME;
import static intercom.home.test.utils.AmazonS3Constants.S3_OBJECT_KEY_NAME;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    AmazonS3Service amazonS3Service;
    @Autowired
    DistanceCalculatorService distanceCalculatorService;

    @Override
    public List<CustomerResponse> getCustomersNearDublin() throws IOException {
        S3Object s3object = amazonS3Service.getS3Object(S3_BUCKET_NAME, S3_OBJECT_KEY_NAME);

        List<CustomerResponse> customersNearDublin = new ArrayList<>();

        Scanner scanner = new Scanner(new InputStreamReader(s3object.getObjectContent()));
        while (scanner.hasNext()) {
            String textLine = scanner.nextLine();
            populateCustomersNearDublin(customersNearDublin, textLine);
        }

        if (!customersNearDublin.isEmpty()) {
            customersNearDublin.sort(Comparator.comparing(CustomerResponse::getUserId));
            return customersNearDublin;
        }

        return new ArrayList<>();
    }

    private void populateCustomersNearDublin(List<CustomerResponse> customersNearDublin, String textLine) throws com.fasterxml.jackson.core.JsonProcessingException {
        CustomerRequest customer = mapper.readValue(textLine, CustomerRequest.class);
        if (isNearDublin(customer.getLatitude(), customer.getLongitude())) {
            CustomerResponse response = new CustomerResponse(customer.getName(), customer.getUserId());
            customersNearDublin.add(response);
        }
    }

    private boolean isNearDublin(double customerLatitude, double customerLongitude) {
        return distanceCalculatorService.calculateDistanceFromDublin(customerLatitude, customerLongitude) <= 100;
    }
}
