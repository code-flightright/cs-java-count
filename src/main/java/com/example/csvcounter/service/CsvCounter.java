package com.example.csvcounter.service;

import com.example.csvcounter.visit.Visit;
import com.example.csvcounter.visit.VisitHasher;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.function.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CsvCounter {

  private final Predicate<Visit> acceptanceCriteria;
  private final VisitHasher hasher;
  private static final CsvMapper MAPPER = new CsvMapper();

  public long countVisits(InputStream input) throws IOException {

    try (MappingIterator<Visit> it = getIterator(input)) {
      HashMap<VisitKeyWrapper, Boolean> knowVisits = new HashMap<>();
      long visits = 0;
      while (it.hasNext()) {
        if (isNewVisit(knowVisits, it.next())) {
          visits++;
        }
      }
      return visits;
    }
  }

  private static MappingIterator<Visit> getIterator(InputStream input)
      throws IOException {
    CsvSchema schema = MAPPER.schemaFor(Visit.class);
    return MAPPER.readerFor(Visit.class)
        .with(schema.withHeader())
        .readValues(input);
  }

  private boolean isNewVisit(HashMap<VisitKeyWrapper, Boolean> knowVisits, Visit visit) {
    return acceptanceCriteria.test(visit)
        && null == knowVisits.putIfAbsent(keyWrap(visit), Boolean.TRUE);
  }

  private VisitKeyWrapper keyWrap(Visit visit) {
    return new VisitKeyWrapper(visit, hasher);
  }

  record VisitKeyWrapper(Visit visit, VisitHasher hasher) {

    @Override
    public boolean equals(Object o) {
      if (o == this) {
        return true;
      }
      if (!(o instanceof VisitKeyWrapper wrapper)) {
        return false;
      }
      return visit != null && hasher != null
          && hasher.equals(wrapper.hasher)
          && hasher.areEqual(visit, wrapper.visit());
    }


    @Override
    public int hashCode() {
      return visit != null ? visit.hashCode() : 0;
    }

  }
}
