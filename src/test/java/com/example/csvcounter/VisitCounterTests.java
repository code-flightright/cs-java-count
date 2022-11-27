package com.example.csvcounter;

import java.util.List;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class VisitCounterTests {

  @Test
  void shouldCountVisits() {
    VisitCounter counter = new VisitCounter();
    long visits = counter.count(List.of(
        new Visit("test@test.com", "123", "google.com"),
        new Visit("testZ@testz.com", "123z", "google.com"),
        new Visit("test1@test.com", "321", "google.com")));
    Assertions.assertThat(visits).isEqualTo(3);
  }

}
