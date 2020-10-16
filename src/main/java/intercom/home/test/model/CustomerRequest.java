package intercom.home.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerRequest {
  @JsonProperty(value = "user_id")
  private Integer userId;

  private String name;
  private Double latitude;
  private Double longitude;
}
