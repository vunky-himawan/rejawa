package com.hotel_reservation.domain.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "transaction_details")
public class TransactionDetail {
    /**
     * The primary key of the class.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The transaction associated with the transaction detail.
     *
     * @see Transaction
     * @see ManyToOne
     * @see JoinColumn
     */
    @ManyToOne
    @JoinColumn(name = "transaction_id", nullable = false)
    private Transaction transaction;

    /**
     * The room associated with the transaction detail.
     *
     * @see Room
     * @see ManyToOne
     * @see JoinColumn
     */
    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    /**
     * The quantity of the transaction detail.
     */
    @Column(name = "quantity", nullable = false)
    private int quantity;

    /**
     * The price of the transaction detail.
     */
    @Column(name = "price", nullable = false)
    private int price;

    /**
     * Constructor for the TransactionDetail class.
     *
     * @param transactionDetailTransaction The transaction associated with the
     *                                     transaction detail.
     * @param transactionDetailRoom        The room associated with the
     *                                     transaction detail.
     * @param transactionDetailQuantity    The quantity of the transaction
     *                                     detail.
     * @param transactionDetailPrice       The price of the transaction detail.
     */
    public TransactionDetail(final Transaction transactionDetailTransaction,
            final Room transactionDetailRoom,
            final int transactionDetailQuantity,
            final int transactionDetailPrice) {
        this.transaction = transactionDetailTransaction;
        this.room = transactionDetailRoom;
        this.quantity = transactionDetailQuantity;
        this.price = transactionDetailPrice;
    }
}
