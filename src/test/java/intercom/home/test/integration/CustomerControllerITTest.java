package intercom.home.test.integration;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestInstance(Lifecycle.PER_CLASS)
class CustomerControllerITTest {

  private MockMvc mockMvc;

  @Autowired private WebApplicationContext webApplicationContext;

  @BeforeAll
  public void setup() {

    this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
  }

  @Test
  void getCustomersNearDublinSuccessTest() throws Exception {
    // arrange
    String expectedContent =
        "[{\"name\":\"Ian Kehoe\",\"userId\":4},"
            + "{\"name\":\"Nora Dempsey\",\"userId\":5},{\"name\":\"Theresa Enright\",\"userId\":6},"
            + "{\"name\":\"Eoin Ahearn\",\"userId\":8},{\"name\":\"Richard Finnegan\",\"userId\":11},"
            + "{\"name\":\"Christina McArdle\",\"userId\":12},{\"name\":\"Olive Ahearn\",\"userId\":13},"
            + "{\"name\":\"Michael Ahearn\",\"userId\":15},{\"name\":\"Eoin Gallagher\",\"userId\":23},"
            + "{\"name\":\"Rose Enright\",\"userId\":24},{\"name\":\"Alan Behan\",\"userId\":31},"
            + "{\"name\":\"Lisa Ahearn\",\"userId\":39}]";

    //act & assert
    this.mockMvc
        .perform(MockMvcRequestBuilders.get("/").accept(MediaType.APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().json(expectedContent));
  }
}
