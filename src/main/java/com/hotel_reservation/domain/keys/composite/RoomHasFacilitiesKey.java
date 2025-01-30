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
    private Long roomId;
    private Long facilityId;
}
