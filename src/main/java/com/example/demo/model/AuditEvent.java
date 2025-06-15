package com.example.demo.model;

import lombok.Data;

@Data
public class AuditEvent {
  private String event;
  private String timestamp;
  private String user;
  private String details;
}
