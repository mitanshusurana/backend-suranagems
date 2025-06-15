package com.example.demo.service;

import com.example.demo.model.GemReport;
import com.mongodb.MongoWriteException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.example.demo.repository.GemReportRepository;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GemReportService {

    private final GemReportRepository gemReportRepository;

    @Autowired
    public GemReportService(GemReportRepository gemReportRepository) {
        this.gemReportRepository = gemReportRepository;
    }

    // Create or Update Gem Report
    public GemReport createOrUpdateGemReport(GemReport gemReport) {
        return gemReportRepository.save(gemReport);
    }

    private static final Logger log = LoggerFactory.getLogger(GemReportService.class);

    public GemReport createGemReport(GemReport gemReport) {
        try {
            return gemReportRepository.insert(gemReport);
        } catch (DuplicateKeyException e) {
            // Handle the duplicate key exception
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Gem report with ID " + gemReport.getId() + " already exists.");
        } catch (Exception e) {
            // Handle any other exceptions
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "An unexpected error occurred while creating the gem report.", e);

        }
    }

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

    // Paginated and filtered fetch
    public Page<GemReport> getGemReports(
            int page,
            int limit,
            String search,
            String category,
            String type,
            String origin,
            String color,
            Double minWeight,
            Double maxWeight,
            List<String> tags
    ) {
        Pageable pageable = PageRequest.of(page - 1, limit);
        Map<String, Object> filters = new HashMap<>();

        // Flexible filters
        if (search != null && !search.isEmpty()) {
            filters.put("name", search); // name will be handled as regex in repo
        }
        if (category != null && !category.isEmpty()) {
            filters.put("category", category);
        }
        if (type != null && !type.isEmpty()) {
            filters.put("type", type);
        }
        if (origin != null && !origin.isEmpty()) {
            filters.put("origin", origin);
        }
        if (color != null && !color.isEmpty()) {
            filters.put("color", color);
        }
        if (minWeight != null) {
            filters.put("minWeight", minWeight);
        }
        if (maxWeight != null) {
            filters.put("maxWeight", maxWeight);
        }
        if (tags != null && !tags.isEmpty()) {
            filters.put("tags", tags);
        }
        log.info("Filters: {}", filters);
        return gemReportRepository.filterGemReports(filters, pageable);
    }

}