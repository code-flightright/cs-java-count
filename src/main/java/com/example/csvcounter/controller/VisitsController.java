package com.example.csvcounter.controller;

import com.example.csvcounter.controller.VisitsResult.VisitSummary;
import com.example.csvcounter.service.CsvCounter;
import java.nio.file.Files;
import java.nio.file.Path;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/visits")
@Log4j2
public class VisitsController {

  private final CsvCounter loader;

  @PostMapping
  @SneakyThrows
  public VisitsResult ingestVisits(@RequestParam final MultipartFile file) {
    Path filePath = Path.of(file.getName());
    file.transferTo(filePath);
    var visits = loader.countVisits(Files.newInputStream(filePath));
    return new VisitsResult(new VisitSummary(visits));
  }
}
