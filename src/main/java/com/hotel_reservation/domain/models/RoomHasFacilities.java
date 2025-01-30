package com.hotel_reservation.domain.models;

import com.hotel_reservation.domain.keys.composite.RoomHasFacilitiesKey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_has_facilities")
public class RoomHasFacilities {
    @EmbeddedId
    private RoomHasFacilitiesKey id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @MapsId("facilityId")
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    public RoomHasFacilities(Room room, Facility facility) {
        this.id = new RoomHasFacilitiesKey(room.getId(), facility.getId());
        this.room = room;
        this.facility = facility;
    }
}
