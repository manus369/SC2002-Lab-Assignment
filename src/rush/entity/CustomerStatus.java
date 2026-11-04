package rush.entity;

/**
 * Lifecycle of a customer, from arrival to payment or leaving.
 */
public enum CustomerStatus {
    WAITING,
    SEATED,
    ORDERED,
    SERVED,
    READY_TO_PAY,
    PAID,
    LEFT
}
