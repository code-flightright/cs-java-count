package com.example.csvcounter.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.ByteArrayInputStream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class CsvCounterTests {

  private CsvCounter counter;

  @BeforeEach
  void setup() {
    counter = new CsvCounter();
  }

  @Test
  @SneakyThrows
  void shouldNotCountNullables() {
    var input = """
        phone,email,source
        test@test.com,,google.com
        nest@nest.com,444,
        ,444,google.com
        null,,null
        """;

    var visits = counter.countVisits(new ByteArrayInputStream(input.getBytes()));
    assertThat(visits).isZero();
  }

  @Test
  @SneakyThrows
  void shouldReadInputStream() {
    var input = new ClassPathResource("test_100mb.csv").getInputStream();
    var visits = counter.countVisits(input);
    assertThat(visits).isNotZero();
  }

}
