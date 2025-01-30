package com.hotel_reservation.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    /**
     * The primary key of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the user.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The email of the user.
     */
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    /**
     * The password of the user.
     */
    @Column(name = "password", nullable = false)
    private String password;

    /**
     * The date and time when the user was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * The date and time when the user was last updated.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * The date and time when the user was deleted.
     */
    @Column(name = "deleted_at", nullable = true)
    private LocalDateTime deletedAt;

    /**
     * The list of sessions associated with the user.
     *
     * @see Session
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<Session> sessions;

    /**
     * The role associated with the user.
     *
     * @see Role
     * @see ManyToOne
     * @see JoinColumn
     * @see JsonIgnore
     */
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    /**
     * The list of activity logs associated with the user.
     *
     * @see ActivityLog
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<ActivityLog> activityLogs;

    /**
     * The list of transactions associated with the user.
     *
     * @see Transaction
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "user",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<Transaction> transactions;

    /**
     * Constructor for the User class.
     *
     * @param userName     The name of the user.
     * @param userEmail    The email of the user.
     * @param userPassword The password of the user.
     */
    public User(
        final String userName,
        final String userEmail,
        final String userPassword) {
        this.name = userName;
        this.email = userEmail;
        this.password = userPassword;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * This method is called when an instance of the class is created.
     *
     * @see PrePersist
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * This method is called when an instance of the class is updated.
     *
     * @see PreUpdate
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
