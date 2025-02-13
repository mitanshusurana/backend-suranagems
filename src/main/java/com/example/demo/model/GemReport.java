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
  private String reportNumber;
  private String date;
  private String colour;
  private String species;
  private String item;
  private String variety;
  private double weight;
  private String origin;
  private String shape;
  private String condition;
  private String cut;
  private String measurements;
  private String transparency;
  private List<String> images;
}