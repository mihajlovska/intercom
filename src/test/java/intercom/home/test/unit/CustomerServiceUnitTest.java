package intercom.home.test.unit;

import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.StringInputStream;
import com.fasterxml.jackson.core.JsonProcessingException;
import static intercom.home.test.common.TestConstants.S3_BUCKET_NAME;
import static intercom.home.test.common.TestConstants.S3_KEY_NAME;
import static intercom.home.test.common.TestConstants.TEST_BUCKET_NAME;
import static intercom.home.test.common.TestConstants.TEST_KEY_NAME;
import intercom.home.test.model.CustomerResponse;
import intercom.home.test.service.AmazonS3Service;
import intercom.home.test.service.CustomerService;
import intercom.home.test.service.DistanceCalculatorService;
import intercom.home.test.service.impl.CustomerServiceImpl;
import static intercom.home.test.utils.common.ErrorMessages.ERROR_OBJECT_IS_NOT_FOUND;
import static intercom.home.test.utils.common.ErrorMessages.ERROR_UNRECOGNIZED_TOKEN;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

class CustomerServiceUnitTest {
  @InjectMocks CustomerService customerService = new CustomerServiceImpl();
  @Mock AmazonS3Service amazonS3Service;
  @Mock DistanceCalculatorService distanceCalculatorService;

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetCustomersNearDublinWithCorrectS3ObjectAndNoPopulatedResultsShouldReturnEmptyList()
      throws NoSuchFieldException, JsonProcessingException, UnsupportedEncodingException {

    // arrange
    String content = "{\"name\":\"value\"}";
    S3Object s3Object = generateTestS3Object(content);

    Mockito.when(amazonS3Service.getS3Object(any(), any())).thenReturn(s3Object);
    Mockito.when(distanceCalculatorService.calculateDistanceFromDublin(any(), any()))
        .thenReturn(200.00);

    // act
    List<CustomerResponse> actualList =
        customerService.getCustomersNearDublin(S3_BUCKET_NAME, S3_KEY_NAME);

    // assert
    assertTrue(actualList.isEmpty());
  }

  @Test
  void testGetCustomersNearDublinWithNullS3ObjectShouldReturnEmptyList() {

    // arrange
    Mockito.when(amazonS3Service.getS3Object(any(), any())).thenReturn(null);
    Mockito.when(distanceCalculatorService.calculateDistanceFromDublin(any(), any()))
        .thenReturn(200.00);

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            NoSuchFieldException.class,
            () -> customerService.getCustomersNearDublin(S3_BUCKET_NAME, S3_KEY_NAME));
    assertEquals(ERROR_OBJECT_IS_NOT_FOUND, exception.getMessage());
  }

  @Test
  void testGetCustomersNearDublinSuccess()
      throws NoSuchFieldException, JsonProcessingException, UnsupportedEncodingException {

    // arrange
    String content =
        "{\"latitude\": \"52.986375\", \"user_id\": 12, \"name\": \"Christina McArdle\", "
            + "\"longitude\": \"-6.043701\"}";
    S3Object s3Object = generateTestS3Object(content);

    Mockito.when(amazonS3Service.getS3Object(any(), any())).thenReturn(s3Object);
    Mockito.when(distanceCalculatorService.calculateDistanceFromDublin(any(), any()))
        .thenReturn(50.00);

    // act
    List<CustomerResponse> actualList =
        customerService.getCustomersNearDublin(S3_BUCKET_NAME, S3_KEY_NAME);

    // assert
    assertNotNull(actualList);
    assertFalse(actualList.isEmpty());
    assertEquals(1, actualList.size());
  }

  @Test
  void testGetCustomersNearDublinWithIncorrectContentShouldThrowException()
      throws UnsupportedEncodingException {

    // arrange
    String content = "Content with incorrect JSON Format";
    S3Object s3Object = generateTestS3Object(content);

    Mockito.when(amazonS3Service.getS3Object(any(), any())).thenReturn(s3Object);
    Mockito.when(distanceCalculatorService.calculateDistanceFromDublin(any(), any()))
        .thenReturn(200.00);

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            JsonProcessingException.class,
            () -> customerService.getCustomersNearDublin(S3_BUCKET_NAME, S3_KEY_NAME));
    assertTrue(exception.getMessage().contains(ERROR_UNRECOGNIZED_TOKEN));
  }

  private S3Object generateTestS3Object(String content) throws UnsupportedEncodingException {
    InputStream inputStream = new StringInputStream(content);

    S3Object s3Object = new S3Object();
    s3Object.setBucketName(TEST_BUCKET_NAME);
    s3Object.setKey(TEST_KEY_NAME);
    s3Object.setObjectContent(inputStream);
    return s3Object;
  }
}
