package com.hotel_reservation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Map;

import com.hotel_reservation.utils.JsonConverter;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "activity_logs")
public class ActivityLog {
    /**
     * The primary key of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The action that was performed.
     * This can be used to track the type of action performed.
     * For example, if the action is "create", it means that a new resource was
     * created.
     */
    @Column(name = "action", nullable = false)
    private String action;

    /**
     * The name of the log.
     * This can be used to identify the type of log.
     * For example, if the log name is "user", it means that the log is related
     * to a user.
     */
    @Column(name = "log_name", nullable = false)
    private String logName;

    /**
     * The description of the log.
     * This can be used to provide more details about the log.
     */
    @Column(name = "log_description", nullable = false)
    private String logDescription;

    /**
     * The properties of the log.
     * This can be used to provide additional information about the log.
     * For example, if the log properties are {"userId": 1, "action": "create"},
     * it means that the log is related to a user with ID 1 and the action
     * performed was "create".
     */
    @Convert(converter = JsonConverter.class)
    @Column(name = "properties", nullable = false)
    private Map<String, Object> properties;

    /**
     * The type of the subject associated with the log.
     * This can be used to identify the type of subject.
     * For example, if the subject type is "user", it means that the log is
     * related to a user.
     * This is useful when you want to filter logs based on the subject type.
     */
    @Column(name = "subject_type", nullable = false)
    private String subjectType;

    /**
     * The date and time when the log was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The user associated with the log.
     *
     * @see User
     * @see ManyToOne
     * @see JoinColumn
     * @see FetchType
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Constructor for the ActivityLog class.
     *
     * @param activityLogUser           The user associated with the log.
     * @param activityLogAction         The action that was performed.
     * @param activityLogLogName        The name of the log.
     * @param activityLogLogDescription The description of the log.
     * @param activityLogProperties     The properties of the log.
     * @param activityLogSubjectType    The type of the subject
     *                                  associated with the
     *                                  log.
     */
    public ActivityLog(final User activityLogUser,
            final String activityLogAction,
            final String activityLogLogName,
            final String activityLogLogDescription,
            final Map<String, Object> activityLogProperties,
            final String activityLogSubjectType) {
        this.user = activityLogUser;
        this.action = activityLogAction;
        this.logName = activityLogLogName;
        this.logDescription = activityLogLogDescription;
        this.properties = activityLogProperties;
        this.subjectType = activityLogSubjectType;
    }

    /**
     * This method is called when an instance of the class is created.
     *
     * @see PrePersist
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
