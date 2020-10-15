package intercom.home.test.service.impl;

import intercom.home.test.service.DistanceCalculatorService;
import static intercom.home.test.utils.DistanceConstants.DUBLIN_LATITUDE;
import static intercom.home.test.utils.DistanceConstants.DUBLIN_LONGITUDE;
import static intercom.home.test.utils.DistanceConstants.RAD_CONVERT;
import org.springframework.stereotype.Service;

@Service
public class DistanceCalculatorServiceImpl implements DistanceCalculatorService {

    @Override
    public double calculateDistanceFromDublin(double customerLatitude, double customerLongitude) {
        double dLat = Math.toRadians(customerLatitude - DUBLIN_LATITUDE);
        double dLon = Math.toRadians(customerLongitude - DUBLIN_LONGITUDE);

        customerLatitude = Math.toRadians(customerLatitude);
        customerLatitude = Math.toRadians(customerLatitude);

        double haversineFormulaCalculation = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(customerLatitude) *
                        Math.cos(customerLatitude);
        return RAD_CONVERT * (2 * Math.asin(Math.sqrt(haversineFormulaCalculation)));
    }

}
