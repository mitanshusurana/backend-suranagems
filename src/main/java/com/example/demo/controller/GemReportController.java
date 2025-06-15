package com.example.demo.controller;

import com.example.demo.model.GemReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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

    @PutMapping
    public ResponseEntity<GemReport> updateGemReport(@RequestBody GemReport gemReport) {
        GemReport createdGemReport = gemReportService.createOrUpdateGemReport(gemReport);
        return new ResponseEntity<>(createdGemReport, HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<GemReport> createGemReport(@RequestBody GemReport gemReport) {
        GemReport createdGemReport = gemReportService.createGemReport(gemReport);
        return new ResponseEntity<>(createdGemReport, HttpStatus.CREATED);
    }

    // Get Gem Report by ID
    @GetMapping("/{id}")
    public ResponseEntity<GemReport> getGemReportById(@PathVariable String id) {
        Optional<GemReport> gemReport = gemReportService.getGemReportById(id);
        return gemReport.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all Gem Reports
    @GetMapping()
    public Page<GemReport> getReports(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int limit,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String category,
            @RequestParam(required = false) String type,
            @RequestParam(required = false) String origin,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) Double minWeight,
            @RequestParam(required = false) Double maxWeight,
            @RequestParam(required = false) List<String> tags
    ) {
        return gemReportService.getGemReports(page, limit, search, category, type, origin, color, minWeight, maxWeight, tags);
    }


    // Delete Gem Report by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGemReport(@PathVariable String id) {
        gemReportService.deleteGemReportById(id);
        return ResponseEntity.noContent().build();
    }
}
