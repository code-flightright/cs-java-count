package com.example.csvcounter;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class CsvLoader {

  private static final CsvMapper MAPPER = new CsvMapper();

  public List<Visit> load(InputStream input) throws IOException {

    CsvSchema schema = MAPPER.schemaFor(Visit.class);
    try (MappingIterator<Visit> it = MAPPER.readerFor(Visit.class)
        .with(schema.withHeader())
        .readValues(input)) {
      return it.readAll();
    }

  }
}
