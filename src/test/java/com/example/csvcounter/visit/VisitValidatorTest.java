package com.example.csvcounter.visit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class VisitValidatorTest {

  @ParameterizedTest
  @CsvSource(textBlock = """
      email,phone,
      ,phone,source
      email,,source
      """)
  void shouldReturnFalseIfAnyEmpty(String email, String phone, String source) {
    var visit = new Visit(email, phone, source);
    boolean result = VisitValidator.hasNoEmptyFields(visit);
    assertThat(result).isFalse();
  }
}