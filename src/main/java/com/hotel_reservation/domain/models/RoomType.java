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
@Table(name = "room_types")
public class RoomType {

    /**
     * The unique identifier of the room type.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the room type (e.g., "Deluxe", "Suite", "Standard").
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The timestamp when the room type was created.
     * This field is automatically set when a new record is inserted.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The timestamp when the room type was last updated.
     * This field is updated automatically whenever the entity is modified.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * The timestamp when the room type was marked as deleted.
     * If this field is null, the room type is considered active.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * The list of rooms associated with this room type.
     * The relationship is mapped using the "roomType" field in the {@link Room}
     * entity.
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "roomType",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<Room> rooms;

    /**
     * Constructs a new RoomType with the given name.
     *
     * @param roomTypeName The name of the room type.
     */
    public RoomType(final String roomTypeName) {
        this.name = roomTypeName;
    }

    /**
     * Sets timestamps before persisting a new entity.
     * This method is automatically called
     * before inserting a new record into the
     * database.
     *
     * @see PrePersist
     */
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    /**
     * Updates the timestamp before updating an existing entity.
     * This method is automatically called
     * before updating a record in the database.
     *
     * @see PreUpdate
     */
    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }
}
