package com.hotel_reservation.domain.models;

import com.hotel_reservation.domain.keys.composite.RoomHasFacilitiesKey;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "room_has_facilities")
public class RoomHasFacilities {
    /**
     * The composite key of the entity.
     */
    @EmbeddedId
    private RoomHasFacilitiesKey id;

    /**
     * The room that has the facility.
     *
     * @see Room
     * @see ManyToOne
     * @see JoinColumn
     * @see MapsId
     */
    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    /**
     * The facility that is in the room.
     *
     * @see Facility
     * @see ManyToOne
     * @see JoinColumn
     * @see MapsId
     */
    @ManyToOne
    @MapsId("facilityId")
    @JoinColumn(name = "facility_id", nullable = false)
    private Facility facility;

    /**
     * Constructor for the entity.
     *
     * @param roomHasFacilitiesRoom     The room that has the facility.
     * @param roomHasFacilitiesFacility The facility that is in the room.
     */
    public RoomHasFacilities(
        final Room roomHasFacilitiesRoom,
        final Facility roomHasFacilitiesFacility) {
        this.id = new RoomHasFacilitiesKey(room.getId(), facility.getId());
        this.room = roomHasFacilitiesRoom;
        this.facility = roomHasFacilitiesFacility;
    }
}
