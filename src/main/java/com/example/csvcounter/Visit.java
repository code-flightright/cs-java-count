package com.example.csvcounter;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
public class Visit {

  String email;
  String phoneNumber;
  @EqualsAndHashCode.Exclude
  String source;

  public boolean hasNull() {
    return null == email || null == phoneNumber || null == source;
  }
}
