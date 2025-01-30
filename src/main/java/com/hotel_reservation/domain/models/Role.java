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
@Table(name = "roles")
public class Role {
    /**
     * The primary key of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the role.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * The date and time when the role was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the role was last updated.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * The date and time when the role was deleted.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * The list of role has permissions associated with the role.
     *
     * @see RoleHasPermissions
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "role",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<RoleHasPermissions> roleHasPermissions;

    /**
     * The list of users associated with the role.
     *
     * @see User
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "role",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<User> users;

    /**
     * Constructor for the Role class.
     *
     * @param roleName The name of the role.
     */
    public Role(final String roleName) {
        this.name = roleName;
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
