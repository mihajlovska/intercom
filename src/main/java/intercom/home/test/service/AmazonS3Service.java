package intercom.home.test.service;

import com.amazonaws.services.s3.model.S3Object;

public interface AmazonS3Service {
  S3Object getS3Object(String bucketName, String keyName);
}
