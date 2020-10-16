package intercom.home.test.service.impl;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import intercom.home.test.service.AmazonS3Service;
import org.springframework.stereotype.Service;

@Service
public class AmazonS3ServiceImpl implements AmazonS3Service {

  @Override
  public S3Object getS3Object(String bucketName, String keyName) {
    AmazonS3 amazonS3Client =
        AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
    return amazonS3Client.getObject(new GetObjectRequest(bucketName, keyName));
  }
}
