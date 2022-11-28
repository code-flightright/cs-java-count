package com.example.csvcounter;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class CsvCounterApplicationTests {

  @Autowired
  private MockMvc mvc;

  @Test
  void contextLoads() {
    assert true;
  }

  @Test
  @SneakyThrows
  void shouldCountVisits() {
    var content = new ClassPathResource("test_100mb.csv").getInputStream();
    MockMultipartFile file = new MockMultipartFile("file", "test_100mb", "csv", content);
    mvc.perform(
            multipart("/visits").file(file)
        ).andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.visits.total").value(Matchers.greaterThan(100)));
  }

}
