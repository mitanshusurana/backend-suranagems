package com.example.demo.repository.custom;

import com.example.demo.model.GemReport;
import com.mongodb.BasicDBObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@Repository
public class GemReportCustomRepositoryImpl implements GemReportCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Page<GemReport> filterGemReports(Map<String, Object> filters, Pageable pageable) {
        try {
            List<Criteria> criteriaList = new ArrayList<>();

            filters.forEach((key, value) -> {
                if (value != null) {
                    switch (key) {
                        case "name":
                        case "category":
                        case "type":
                        case "color":
                        case "origin":
                        case "clarity":
                        case "cut":
                        case "certification":
                        case "seller":
                        case "createdBy":
                            criteriaList.add(Criteria.where(key).regex(value.toString(), "i"));
                            break;
                        case "minWeight":
                            criteriaList.add(Criteria.where("weight").gte((Double) value));
                            break;
                        case "maxWeight":
                            criteriaList.add(Criteria.where("weight").lte((Double) value));
                            break;
                        case "tags":
                            criteriaList.add(Criteria.where("tags").in((List<String>) value));
                            break;
                        default:
                            log.warn("Unknown filter field: {}", key);
                    }
                }
            });

            Criteria finalCriteria = new Criteria();
            if (!criteriaList.isEmpty()) {
                finalCriteria = new Criteria().andOperator(criteriaList.toArray(new Criteria[0]));
            }
            Query query = new Query(finalCriteria).with(pageable);
            List<GemReport> resultList = mongoTemplate.find(query, GemReport.class);
            long total = mongoTemplate.count(new Query(finalCriteria), GemReport.class);

            return new PageImpl<>(resultList, pageable, total);

        } catch (Exception e) {
            log.error("Error while filtering GemReports", e);
            return Page.empty(); // Or throw custom exception if preferred
        }
    }
}
