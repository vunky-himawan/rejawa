package com.hotel_reservation.domain.enums;

public enum TransactionStatus {

    /**
     * The transaction is pending and awaiting further action.
     */
    PENDING,

    /**
     * The transaction has been confirmed successfully.
     */
    CONFIRMED,

    /**
     * The transaction has been cancelled.
     */
    CANCELLED
}
