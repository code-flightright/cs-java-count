package com.example.csvcounter.visit;

import static org.springframework.util.StringUtils.hasText;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Visit {

  String email;
  String phoneNumber;
  @EqualsAndHashCode.Exclude
  String source;

  public boolean hasNull() {
    return !(hasText(email) && hasText(phoneNumber) && hasText(source));
  }
}
