package com.example.demo.controller;

import com.example.demo.model.GemReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.service.GemReportService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/gem-reports")
public class GemReportController {

    private final GemReportService gemReportService;

    @Autowired
    public GemReportController(GemReportService gemReportService) {
        this.gemReportService = gemReportService;
    }

    // Create or Update Gem Report
    @PostMapping
    public ResponseEntity<GemReport> createGemReport(@RequestBody GemReport gemReport) {
        GemReport createdGemReport = gemReportService.createOrUpdateGemReport(gemReport);
        return new ResponseEntity<>(createdGemReport, HttpStatus.CREATED);
    }

    // Get Gem Report by ID
    @GetMapping("/{id}")
    public ResponseEntity<GemReport> getGemReportById(@PathVariable String id) {
        Optional<GemReport> gemReport = gemReportService.getGemReportById(id);
        return gemReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Gem Reports
    @GetMapping
    public List<GemReport> getAllGemReports() {
        return gemReportService.getAllGemReports();
    }

    // Delete Gem Report by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGemReport(@PathVariable String id) {
        gemReportService.deleteGemReportById(id);
        return ResponseEntity.noContent().build();
    }
}
