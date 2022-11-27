package com.example.csvcounter.visit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PhoneAndMailInspectorTest {

  private PhoneAndMailHasher inspector;

  @BeforeEach
  void setup() {
    inspector = new PhoneAndMailHasher();
  }

  @Test
  void shouldBeEqualDespiteSource() {
    Visit visit = Visit.builder().email("mail").phoneNumber("phone").source("s").build();
    Visit other = Visit.builder().email("mail").phoneNumber("phone").build();

    boolean result = inspector.areEqual(visit, other);
    assertThat(result).isTrue();
  }

  @ParameterizedTest
  @CsvSource({
      "mail,,mail,",
      ",phone,,phone",
      ",phone,mail,phone",
      "mail,phone,,phone",
      "mail,,mail,phone",
      "mail,phone,mail,",
      ",phone,mail,phone",
      ",,,"
  })
  void shouldNotBeEqualsIfNulls(String mail, String otherMail, String phone, String otherPhone) {
    Visit visit = Visit.builder().email(mail).phoneNumber(phone).build();
    Visit other = Visit.builder().email(otherMail).phoneNumber(otherPhone).build();

    boolean result = inspector.areEqual(visit, other);
    assertThat(result).isFalse();
  }

  @ParameterizedTest
  @CsvSource({
      "mail,phone,source,source",
      "mail2,phone2,source,",
      "abc@def, 123,source,other source"
  })
  void shouldHaveSameHashDespiteSource(String mail, String phone, String source,
      String otherSource) {
    Visit visit = Visit.builder().email(mail).phoneNumber(phone).source(source).build();
    Visit other = Visit.builder().email(mail).phoneNumber(phone).source(otherSource).build();

    int hash = inspector.hash(visit);
    int otherHash = inspector.hash(other);
    assertThat(hash).isEqualTo(otherHash);
  }

}