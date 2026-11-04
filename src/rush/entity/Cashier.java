package rush.entity;

/**
 * Automatically collects at most one payment per turn, oldest READY_TO_PAY customer first.
 */
public class Cashier extends Staff {

    public Cashier() {
        super("Cashier");
    }

    /**
     * If the cashier is free and someone is ready to pay, marks their order and themselves PAID,
     * records the payment in the restaurant and returns the customer; otherwise returns null.
     */
    public Customer collectPayment(Restaurant restaurant) {
        // TODO
        return null;
    }
}
