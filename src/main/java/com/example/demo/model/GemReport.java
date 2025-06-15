package com.example.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "gemReports")
@Data
public class GemReport {

    @Id
    private String id;
    private String name;
    private String category;
    private String type;
    private double weight; // in carats
    private Dimensions dimensions;
    private String color;
    private String clarity;
    private String cut;
    private String origin;
    private String treatment;
    private String certification;
    private String acquisitionDate;
    private Double acquisitionPrice;
    private Double estimatedValue;
    private String seller;
    private String notes;
    private List<String> tags;
    private List<String> images;
    private String video;
    private String qrCode;
    private String createdAt;
    private String updatedAt;
    private String createdBy;
    private String lastEditedBy;
    private List<AuditEvent> auditTrail;
}


