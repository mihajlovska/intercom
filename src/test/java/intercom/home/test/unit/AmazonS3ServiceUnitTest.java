package intercom.home.test.unit;

import com.amazonaws.services.s3.model.AmazonS3Exception;
import com.amazonaws.services.s3.model.S3Object;
import static intercom.home.test.common.TestConstants.INCORRECT_VALUE;
import static intercom.home.test.common.TestConstants.S3_BUCKET_NAME;
import static intercom.home.test.common.TestConstants.S3_KEY_NAME;
import intercom.home.test.service.AmazonS3Service;
import intercom.home.test.service.impl.AmazonS3ServiceImpl;
import static intercom.home.test.utils.common.ErrorMessages.BUCKET_NAME_FIELD;
import static intercom.home.test.utils.common.ErrorMessages.ERROR_ACCESS_DENIED;
import static intercom.home.test.utils.common.ErrorMessages.ERROR_INVALID_BUCKET_SPECIFIED;
import static intercom.home.test.utils.common.ErrorMessages.ERROR_INVALID_VALUE;
import static intercom.home.test.utils.common.ErrorMessages.KEY_NAME_FIELD;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

class AmazonS3ServiceUnitTest {
  @InjectMocks AmazonS3Service amazonS3Service = new AmazonS3ServiceImpl();

  @BeforeEach
  public void init() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  void testGetS3ObjectWithCorrectValuesShouldReturnS3Object() {

    // act
    S3Object s3Object = amazonS3Service.getS3Object(S3_BUCKET_NAME, S3_KEY_NAME);

    // assert
    assertNotNull(s3Object.getObjectContent());
  }

  @Test
  void testGetS3ObjectWithNullValueForBucketNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> amazonS3Service.getS3Object(null, S3_KEY_NAME));
    assertEquals(String.format(ERROR_INVALID_VALUE, BUCKET_NAME_FIELD), exception.getMessage());
  }

  @Test
  void testGetS3ObjectWithNullValueForKeyNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class,
            () -> amazonS3Service.getS3Object(S3_BUCKET_NAME, null));
    assertEquals(String.format(ERROR_INVALID_VALUE, KEY_NAME_FIELD), exception.getMessage());
  }

  @Test
  void testGetS3ObjectWithEmptyValueForBucketNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> amazonS3Service.getS3Object("", S3_KEY_NAME));
    assertEquals(String.format(ERROR_INVALID_VALUE, BUCKET_NAME_FIELD), exception.getMessage());
  }

  @Test
  void testGetS3ObjectWithEmptyValueForKeyNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> amazonS3Service.getS3Object(S3_BUCKET_NAME, ""));
    assertEquals(String.format(ERROR_INVALID_VALUE, KEY_NAME_FIELD), exception.getMessage());
  }

  @Test
  void testGetS3ObjectWithEmptyValueForBucketAndKeyNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> amazonS3Service.getS3Object("", ""));
    assertEquals(String.format(ERROR_INVALID_VALUE, BUCKET_NAME_FIELD), exception.getMessage());
  }

  @Test
  void testGetS3ObjectWithNullValueForBucketAndKeyNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> amazonS3Service.getS3Object(null, null));
    assertEquals(String.format(ERROR_INVALID_VALUE, BUCKET_NAME_FIELD), exception.getMessage());
  }

  @Test
  void
      testGetS3ObjectWithEmptyValueForBucketNameAndNullValueForKeyNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> amazonS3Service.getS3Object("", null));

    assertEquals(String.format(ERROR_INVALID_VALUE, BUCKET_NAME_FIELD), exception.getMessage());
  }

  @Test
  void
      testGetS3ObjectWithNullValueForBucketNameAndEmptyValueForKeyNameShouldThrowIllegalArgumentException() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            IllegalArgumentException.class, () -> amazonS3Service.getS3Object(null, ""));
    assertEquals(String.format(ERROR_INVALID_VALUE, BUCKET_NAME_FIELD), exception.getMessage());
  }

  @Test
  void testGetS3ObjectWithIncorrectValueForBucketNameShouldThrowAmazonS3Exception() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            AmazonS3Exception.class,
            () -> amazonS3Service.getS3Object(INCORRECT_VALUE, S3_KEY_NAME));
    assertTrue(exception.getMessage().contains(ERROR_INVALID_BUCKET_SPECIFIED));
  }

  @Test
  void testGetS3ObjectWithIncorrectValueForKeyNameShouldThrowAmazonS3Exception() {

    // act & assert
    Exception exception =
        Assertions.assertThrows(
            AmazonS3Exception.class,
            () -> amazonS3Service.getS3Object(S3_BUCKET_NAME, INCORRECT_VALUE));
    assertTrue(exception.getMessage().contains(ERROR_ACCESS_DENIED));
  }
}
