package com.hotel_reservation.domain.keys.composite;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class RoomHasFacilitiesKey implements Serializable {
    /**
     * The ID of the room in the relationship.
     */
    private Long roomId;

    /**
     * The ID of the facility in the relationship.
     */
    private Long facilityId;
}
