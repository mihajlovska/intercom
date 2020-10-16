package intercom.home.test.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResponse {
  private String name;
  private Integer userId;

  public CustomerResponse(String name, Integer userId) {
    this.name = name;
    this.userId = userId;
  }
}
