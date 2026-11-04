package rush.entity;

/**
 * Lifecycle of an order, from the kitchen to payment or cancellation.
 */
public enum OrderStatus {
    PREPARING,
    READY,
    SERVED,
    PAID,
    CANCELLED
}
