package com.hotel_reservation.domain.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
import jakarta.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "permissions")
public class Permission {
    /**
     * The primary key of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The name of the permission.
     */
    @Column(name = "name", nullable = false, unique = true)
    private String name;

    /**
     * The list of roles associated with the permission.
     *
     * @see RoleHasPermissions
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "permission",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<RoleHasPermissions> roleHasPermissions;

    /**
     * Constructor for the Permission class.
     *
     * @param permissionName The name of the permission.
     */
    public Permission(final String permissionName) {
        this.name = permissionName;
    }
}
