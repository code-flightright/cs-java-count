package com.example.csvcounter;

import java.util.Collection;

public class VisitCounter {

  public long count(Collection<Visit> visits) {
    return visits.stream()
        .parallel()
        .unordered()
        .distinct()
        .count();
  }
}
