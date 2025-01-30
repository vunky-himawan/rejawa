package com.hotel_reservation.domain.models;

import com.hotel_reservation.domain.keys.composite.RoleHasPermissionsKey;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "role_has_permissions")
public class RoleHasPermissions {
    @EmbeddedId
    private RoleHasPermissionsKey id;

    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    public RoleHasPermissions(Role role, Permission permission) {
        this.id = new RoleHasPermissionsKey(role.getId(), permission.getId());
        this.role = role;
        this.permission = permission;
    }
}
