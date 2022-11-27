package com.example.csvcounter;

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
    return null == email || null == phoneNumber || null == source;
  }
}
