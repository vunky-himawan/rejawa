package com.hotel_reservation.domain.models;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hotel_reservation.domain.enums.TransactionStatus;

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
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transactions")
public class Transaction {
    /**
     * The primary key of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The total amount of the transaction.
     */
    @Column(name = "total", nullable = false)
    private int total;

    /**
     * The status of the transaction.
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;

    /**
     * The tax amount of the transaction.
     */
    @Column(name = "tax", nullable = false)
    private double tax;

    /**
     * The discount amount of the transaction.
     */
    @Column(name = "discount", nullable = false)
    private double discount;

    /**
     * The date and time when the transaction was created.
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * The date and time when the transaction was last updated.
     */
    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt = LocalDateTime.now();

    /**
     * The date and time when the transaction was deleted.
     */
    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;

    /**
     * The list of transaction details associated with the transaction.
     *
     * @see TransactionDetail
     * @see OneToMany
     * @see CascadeType
     * @see FetchType
     * @see JsonIgnore
     */
    @JsonIgnore
    @OneToMany(
        mappedBy = "transaction",
        cascade = CascadeType.ALL,
        fetch = FetchType.LAZY)
    private List<TransactionDetail> transactionDetails;

    /**
     * The user associated with the transaction.
     *
     * @see User
     * @see ManyToOne
     * @see JoinColumn
     * @see FetchType
     */
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Constructor for the Transaction class.
     *
     * @param transactionUser  The user associated with the transaction.
     * @param transactionTotal The total amount of the transaction.
     */
    public Transaction(final User transactionUser, final int transactionTotal) {
        this.user = transactionUser;
        this.total = transactionTotal;
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
