package com.example.csvcounter.visit;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class Visit {

  String email;
  String phoneNumber;
  String source;

}
