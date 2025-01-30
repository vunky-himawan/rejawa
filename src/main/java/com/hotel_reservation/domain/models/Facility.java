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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "facilities")
public class Facility {
    /**
     * The primary key of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the facility.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The description of the facility.
     */
    @Column(name = "description", nullable = false)
    private String description;

    /**
     * The date and time when the facility was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the facility was last updated.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * The date and time when the facility was deleted.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * The list of rooms associated with the facility.
     *
     * @see RoomHasFacilities
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "facility",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<RoomHasFacilities> roomHasFacilities;

    /**
     * Constructor for the Facility class.
     *
     * @param facilityName        The name of the facility.
     * @param facilityDescription The description of the facility.
     */
    public Facility(
        final String facilityName,
        final String facilityDescription) {
        this.name = facilityName;
        this.description = facilityDescription;
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
