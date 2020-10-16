package intercom.home.test.utils.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AmazonS3Properties {
  @Value("${bucket.name}")
  private String bucketName;

  @Value("${key.name}")
  private String keyName;
}
