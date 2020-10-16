package intercom.home.test.service.impl;

import intercom.home.test.service.DistanceCalculatorService;
import static intercom.home.test.utils.common.DistanceConstants.DUBLIN_LATITUDE;
import static intercom.home.test.utils.common.DistanceConstants.DUBLIN_LONGITUDE;
import static intercom.home.test.utils.common.DistanceConstants.RAD_CONVERT;
import static intercom.home.test.utils.common.ErrorMessages.CUSTOMER_LATITUDE_FIELD;
import static intercom.home.test.utils.common.ErrorMessages.CUSTOMER_LONGITUDE_FIELD;
import static intercom.home.test.utils.common.ErrorMessages.ERROR_INVALID_VALUE;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorServiceImpl implements DistanceCalculatorService {

  @Override
  public double calculateDistanceFromDublin(Double customerLatitude, Double customerLongitude) {
    validateCalculateDistanceFromDublinRequest(customerLatitude, customerLongitude);

    double dLat = Math.toRadians(customerLatitude - DUBLIN_LATITUDE);
    double dLon = Math.toRadians(customerLongitude - DUBLIN_LONGITUDE);

    customerLatitude = Math.toRadians(customerLatitude);
    customerLatitude = Math.toRadians(customerLatitude);

    double haversineFormulaCalculation =
        Math.pow(Math.sin(dLat / 2), 2)
            + Math.pow(Math.sin(dLon / 2), 2)
                * Math.cos(customerLatitude)
                * Math.cos(customerLatitude);
    return RAD_CONVERT * (2 * Math.asin(Math.sqrt(haversineFormulaCalculation)));
  }

  private void validateCalculateDistanceFromDublinRequest(
      Double customerLatitude, Double customerLongitude) {
    if (customerLatitude == null || customerLatitude.isNaN()) {
      throw new IllegalArgumentException(
          String.format(ERROR_INVALID_VALUE, CUSTOMER_LATITUDE_FIELD));
    }
    if (customerLongitude == null || customerLongitude.isNaN()) {
      throw new IllegalArgumentException(
          String.format(ERROR_INVALID_VALUE, CUSTOMER_LONGITUDE_FIELD));
    }
  }
}
