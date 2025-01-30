package com.hotel_reservation.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel_reservation.domain.enums.RoomStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name = "rooms")
public class Room {
    /**
     * The primary key of the room.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the room.
     */
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The capacity of the room.
     */
    @Column(name = "capacity", nullable = false)
    private int capacity;

    /**
     * The price of the room.
     */
    @Column(name = "price", nullable = false)
    private int price;

    /**
     * The status of the room.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RoomStatus status;

    /**
     * The date and time when the room was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    /**
     * The date and time when the room was last updated.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    /**
     * The date and time when the room was deleted.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * The room type of the room.
     *
     * @see RoomType
     * @see ManyToOne
     * @see JoinColumn
     */
    @ManyToOne
    @JoinColumn(name = "room_type_id", nullable = false)
    private RoomType roomType;

    /**
     * The facilities that are available in the room.
     *
     * @see RoomHasFacilities
     * @see OneToMany
     * @see FetchType
     * @see CascadeType
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "room",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<RoomHasFacilities> roomHasFacilities;

    /**
     * The constructor for the Room class.
     *
     * @param roomName
     *                     The name of the room.
     * @param roomCapacity
     *                     The capacity of the room.
     * @param roomPrice
     *                     The price of the room.
     * @param typeOfRoom
     *                     The room type of the room.
     */
    public Room(
        final String roomName,
        final int roomCapacity,
        final int roomPrice,
        final RoomType typeOfRoom) {
        this.name = roomName;
        this.capacity = roomCapacity;
        this.price = roomPrice;
        this.roomType = typeOfRoom;
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
