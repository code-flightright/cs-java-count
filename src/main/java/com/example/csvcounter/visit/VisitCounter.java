package com.example.csvcounter.visit;

import static java.util.function.Predicate.not;

import java.util.Collection;

public class VisitCounter {

  public long count(Collection<Visit> visits) {
    return visits.stream()
        .parallel()
        .unordered()
        .filter(not(Visit::hasNull))
        .distinct()
        .count();
  }
}
