package com.example.demo.repository.custom;

import com.example.demo.model.GemReport;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface GemReportCustomRepository {
    Page<GemReport> filterGemReports(Map<String, Object> filters, Pageable pageable);
}
