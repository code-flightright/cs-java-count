package com.example.csvcounter.visit;

import com.example.csvcounter.visit.Visit;
import com.example.csvcounter.visit.VisitCounter;
import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VisitCounterTests {

  private VisitCounter visitCounter;

  @BeforeEach
  void setup() {
    visitCounter = new VisitCounter();
  }

  @Test
  void shouldCountVisits() {
    long visits = visitCounter.count(List.of(
        new Visit("test@test.com", "123", "google.com"),
        new Visit("testZ@testz.com", "123z", "google.com"),
        new Visit("test1@test.com", "321", "google.com")));
    Assertions.assertThat(visits).isEqualTo(3);
  }

  @Test
  void shouldNotCountRepeatedVisits() {
    long visits = visitCounter.count(List.of(
        new Visit("test@test.com", "123", "google.com"),
        new Visit("test@test.com", "123", "meta.com"),
        new Visit("test1@test.com", "321", "google.com")));
    Assertions.assertThat(visits).isEqualTo(2);
  }

  @Test
  void shouldNotCountNullFields() {
    long visits = visitCounter.count(List.of(
        new Visit("test@test.com", "123", "google.com"),
        new Visit(null, "123", "google.com"),
        new Visit("test@test.com", null, "meta.com"),
        new Visit("test1@test.com", "321", "google.com"),
        new Visit("test1@test.com", "321", null)));
    Assertions.assertThat(visits).isEqualTo(2);

  }

}
