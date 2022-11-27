package com.example.csvcounter.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.example.csvcounter.service.CsvCounter.VisitKeyWrapper;
import com.example.csvcounter.visit.PhoneAndMailHasher;
import com.example.csvcounter.visit.Visit;
import com.example.csvcounter.visit.VisitValidator;
import java.io.ByteArrayInputStream;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

class CsvCounterTests {

  private CsvCounter counter;

  @BeforeEach
  void setup() {
    counter = new CsvCounter(VisitValidator::hasNoEmptyFields, new PhoneAndMailHasher());
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

  @Test
  void wrapperShouldBeEqualIfVisitsAreEquals() {
    PhoneAndMailHasher hasher = new PhoneAndMailHasher();
    var wrapper1 = new VisitKeyWrapper(
        Visit.builder().email("mail").phoneNumber("phone").source("source").build(), hasher);
    var wrapper2 = new VisitKeyWrapper(Visit.builder().email("mail").phoneNumber("phone").build(),
        hasher);

    assertThat(wrapper1).isEqualTo(wrapper2);
  }

  @Test
  void wrapperShouldBeEqualIfSame() {
    var wrapper1 = new VisitKeyWrapper(
        Visit.builder().email("mail").phoneNumber("phone").source("source").build(),
        new PhoneAndMailHasher());

    assertThat(wrapper1).isEqualTo(wrapper1);
  }

}
