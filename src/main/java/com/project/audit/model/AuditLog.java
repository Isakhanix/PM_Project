package com.project.audit.model;

import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="audit_log")
public class AuditLog {
  @Id @GeneratedValue private Long id;

  private String actor;        // username
  private String action;       // QUIZ_SUBMITTED, ANALYTICS_VIEWED
  private String entityType;   // QUIZ_RESULT, ANALYTICS
  private String entityId;     // e.g. resultId
  private Instant createdAt = Instant.now();
}
