package com.example.csvcounter.service;

import com.example.csvcounter.visit.Visit;
import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class CsvCounter {

  private static final CsvMapper MAPPER = new CsvMapper();

  public long countVisits(InputStream input) throws IOException {

    CsvSchema schema = MAPPER.schemaFor(Visit.class);
    try (MappingIterator<Visit> it = MAPPER.readerFor(Visit.class)
        .with(schema.withHeader())
        .readValues(input)) {
      var knowVisits = new HashMap<Visit, Boolean>();
      long visits = 0;
      while (it.hasNext()) {
        if (isNewVisit(knowVisits, it.next())) {
          visits++;
        }
      }
      log.info("visits {}", visits);
      return visits;
    }
  }

  private static boolean isNewVisit(HashMap<Visit, Boolean> knowVisits, Visit visit) {
    return !visit.hasNull() && null == knowVisits.putIfAbsent(visit, Boolean.TRUE);
  }

}
