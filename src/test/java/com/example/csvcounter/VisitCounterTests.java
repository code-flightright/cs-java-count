package com.example.csvcounter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class VisitCounterTests {

  @Test
  void shouldCountVisits() {
    VisitCounter counter = new VisitCounter();
    long visits = counter.count(new Visit("test@test.com", "123", "google.com"));
    Assertions.assertThat(visits).isEqualTo(1);
  }

}
