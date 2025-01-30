package com.hotel_reservation.domain.models;

import com.hotel_reservation.domain.keys.composite.RoleHasPermissionsKey;

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
@Table(name = "role_has_permissions")
public class RoleHasPermissions {

    /**
     * The composite key that uniquely identifies
     * the role-permission relationship.
     *
     * @see RoleHasPermissionsKey
     */
    @EmbeddedId
    private RoleHasPermissionsKey id;

    /**
     * The role associated with this relationship.
     * This field is mapped using the composite key's roleId.
     *
     * @see ManyToOne
     * @see MapsId
     * @see JoinColumn
     */
    @ManyToOne
    @MapsId("roleId")
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    /**
     * The permission associated with this relationship.
     * This field is mapped using the composite key's permissionId.
     *
     * @see ManyToOne
     * @see MapsId
     * @see JoinColumn
     */
    @ManyToOne
    @MapsId("permissionId")
    @JoinColumn(name = "permission_id", nullable = false)
    private Permission permission;

    /**
     * Constructs a new RoleHasPermissions instance.
     *
     * @param roleHasPermissionsRole
     *                              The role that is assigned the permission.
     *
     * @param roleHasPermissionsPermission
     *                              The permission assigned to the role.
     */
    public RoleHasPermissions(
        final Role roleHasPermissionsRole,
        final Permission roleHasPermissionsPermission) {
        this.id = new RoleHasPermissionsKey(role.getId(), permission.getId());
        this.role = roleHasPermissionsRole;
        this.permission = roleHasPermissionsPermission;
    }
}
