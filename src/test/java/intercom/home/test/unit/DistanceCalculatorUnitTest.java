package intercom.home.test.unit;

import intercom.home.test.service.DistanceCalculatorService;
import intercom.home.test.service.impl.DistanceCalculatorServiceImpl;
import static intercom.home.test.utils.common.ErrorMessages.CUSTOMER_LATITUDE_FIELD;
import static intercom.home.test.utils.common.ErrorMessages.CUSTOMER_LONGITUDE_FIELD;
import static intercom.home.test.utils.common.ErrorMessages.ERROR_INVALID_VALUE;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class DistanceCalculatorUnitTest {
  @InjectMocks
  DistanceCalculatorService distanceCalculatorService = new DistanceCalculatorServiceImpl();

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testCalculateDistanceFromDublinSuccess() {

    // arrange
    double latitude = 52.98637;
    double longitude = -6.043701;
    double expectedValue = 45.90323755993316;

    // act
    double distance = distanceCalculatorService.calculateDistanceFromDublin(latitude, longitude);

    // assert
    assertEquals(expectedValue, distance);
  }

  @Test
  void testCalculateDistanceFromDublinWithLatitudeNullValueShouldThrowIllegalArgumentException() {

    // arrange
    double longitude = -6.043701;

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> distanceCalculatorService.calculateDistanceFromDublin(null, longitude));
    assertEquals(
        String.format(ERROR_INVALID_VALUE, CUSTOMER_LATITUDE_FIELD), exception.getMessage());
  }

  @Test
  void testCalculateDistanceFromDublinWithLongitudeNullValueShouldThrowIllegalArgumentException() {

    // arrange
    double latitude = 52.98637;

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> distanceCalculatorService.calculateDistanceFromDublin(latitude, null));
    assertEquals(
        String.format(ERROR_INVALID_VALUE, CUSTOMER_LONGITUDE_FIELD), exception.getMessage());
  }

  @Test
  void
      testCalculateDistanceFromDublinWithLatitudeIncorrectValueShouldThrowIllegalArgumentException() {

    // arrange
    double latitude = 0.0 / 0.0;
    double longitude = -6.043701;

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> distanceCalculatorService.calculateDistanceFromDublin(latitude, longitude));
    assertEquals(
        String.format(ERROR_INVALID_VALUE, CUSTOMER_LATITUDE_FIELD), exception.getMessage());
  }

  @Test
  void
      testCalculateDistanceFromDublinWithLongitudeIncorrectValueShouldThrowIllegalArgumentException() {

    // arrange
    double latitude = 52.98637;
    double longitude = 0.0 / 0.0;

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> distanceCalculatorService.calculateDistanceFromDublin(latitude, longitude));
    assertEquals(
        String.format(ERROR_INVALID_VALUE, CUSTOMER_LONGITUDE_FIELD), exception.getMessage());
  }
}
