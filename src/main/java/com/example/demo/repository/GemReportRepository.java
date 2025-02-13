package com.example.demo.repository;

import com.example.demo.model.GemReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GemReportRepository extends MongoRepository<GemReport, String> {
    // Custom query methods (if needed) can go here
}
