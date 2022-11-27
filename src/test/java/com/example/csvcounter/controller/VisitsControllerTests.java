package com.example.csvcounter.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.csvcounter.service.CsvLoader;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
@MockBean(classes = {CsvLoader.class})
class VisitsControllerTests {

  public static final byte[] INPUT = """
      phone,email,source
      test@test.com,123,google.com
      nest@nest.com,444,google.com
      """
      .getBytes();
  @Autowired
  private MockMvc mvc;

  @Test
  @SneakyThrows
  void shouldReceiveFile() {
    var input = """
        phone,email,source
        test@test.com,123,google.com
        nest@nest.com,444,google.com
        """
        .getBytes();

    mvc.perform(
        multipart("/visits").file(new MockMultipartFile("file", input))
    ).andExpect(
        status().isOk());
  }

  @Test
  @SneakyThrows
  void shouldReturnVisitTotal() {

    mvc.perform(
            multipart("/visits").file(new MockMultipartFile("file", INPUT))
        ).andExpect(
            status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.visits.total").value(2));
  }

}
