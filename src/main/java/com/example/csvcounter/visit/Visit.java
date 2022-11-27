package com.example.csvcounter.visit;

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

}
