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
public class RoleHasPermissionsKey implements Serializable {
    /**
     * The ID of the role in the relationship.
     */
    private Long roleId;

    /**
     * The ID of the permission in the relationship.
     */
    private Long permissionId;
}
