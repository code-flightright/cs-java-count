package com.example.csvcounter.service;

import com.example.csvcounter.visit.Visit;
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

  private static final CsvMapper MAPPER = new CsvMapper();

  public long countVisits(InputStream input) throws IOException {

    try (MappingIterator<Visit> it = getIterator(input)) {
      var knowVisits = new HashMap<Visit, Boolean>();
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

  private boolean isNewVisit(HashMap<Visit, Boolean> knowVisits, Visit visit) {
    return acceptanceCriteria.test(visit) && null == knowVisits.putIfAbsent(visit, Boolean.TRUE);
  }

}
