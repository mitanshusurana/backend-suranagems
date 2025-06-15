package com.example.demo.repository;

import com.example.demo.model.GemReport;
import com.example.demo.repository.custom.GemReportCustomRepository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GemReportRepository extends MongoRepository<GemReport, String>, GemReportCustomRepository {
}
