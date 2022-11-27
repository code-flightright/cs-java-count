package com.example.csvcounter.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.csvcounter.visit.Visit;
import java.io.ByteArrayInputStream;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class CsvLoaderTests {

  private CsvLoader loader;

  @BeforeEach
  void setup() {
    loader = new CsvLoader();
  }

  @Test
  @SneakyThrows
  void shouldParseCsv() {
    var input = """
        phone,email,source
        test@test.com,123,google.com
        nest@nest.com,444,google.com
        """;

    List<Visit> visits = loader.load(new ByteArrayInputStream(input.getBytes()));
    assertThat(visits).contains(
        Visit.builder().email("test@test.com").phoneNumber("123").source("google.com").build(),
        Visit.builder().email("nest@nest.com").phoneNumber("444").source("google.com").build());
  }

  @Test
  @SneakyThrows
  void shouldParseFileWithNulls() {
    var input = """
        phone,email,source
        test@test.com,,google.com
        nest@nest.com,444,
        ,444,google.com
        null,,null
        """;

    List<Visit> visits = loader.load(new ByteArrayInputStream(input.getBytes()));
    assertThat(visits).size().isEqualTo(4);
  }

  @Test
  @SneakyThrows
  void shouldReadInputStream() {
    var input = new ClassPathResource("test_100mb.csv").getInputStream();
    var visits = loader.load(input);
    assertThat(visits).isNotEmpty();
  }

}
