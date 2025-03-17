package com.example.demo.service;

import com.example.demo.model.GemReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.GemReportRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GemReportService {

    private final GemReportRepository gemReportRepository;

    @Autowired
    public GemReportService(GemReportRepository gemReportRepository) {
        this.gemReportRepository = gemReportRepository;
    }

    // Create or Update Gem Report
    public GemReport createOrUpdateGemReport(GemReport gemReport) { return gemReportRepository.save(gemReport); }  public GemReport createOrUpdateGemReport(GemReport gemReport) { return gemReportRepository.create(gemReport); }

    // Get Gem Report by ID
    public Optional<GemReport> getGemReportById(String id) {
        return gemReportRepository.findById(id);
    }

    // Get all Gem Reports
    public List<GemReport> getAllGemReports() {
        return gemReportRepository.findAll();
    }

    // Delete Gem Report by ID
    public void deleteGemReportById(String id) {
        gemReportRepository.deleteById(id);
    }
}
