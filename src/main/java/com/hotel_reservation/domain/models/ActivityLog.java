package com.hotel_reservation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import com.hotel_reservation.utils.JsonConverter;

import jakarta.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activity_logs")
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action", nullable = false)
    private String action;

    @Column(name = "log_name", nullable = false)
    private String logName;

    @Column(name = "log_description", nullable = false)
    private String logDescription;

    @Convert(converter = JsonConverter.class)
    @Column(name = "properties", nullable = false)
    private Map<String, Object> properties;

    @Column(name = "subject_type", nullable = false)
    private String subjectType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public ActivityLog(User user, String action, String logName, String logDescription,
            Map<String, Object> properties, String subjectType) {
        this.user = user;
        this.action = action;
        this.logName = logName;
        this.logDescription = logDescription;
        this.properties = properties;
        this.subjectType = subjectType;
        this.createdAt = LocalDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
