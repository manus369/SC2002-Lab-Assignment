package rush.entity;

/**
 * Seats customers, takes orders and serves READY dishes (one task per turn).
 */
public class Waiter extends Staff {

    public Waiter() {
        super("Waiter");
    }

    /**
     * Seats a waiting customer at a free table via the restaurant.
     */
    public void seatCustomer(Customer customer, Restaurant restaurant) throws InvalidActionException {
        // TODO
    }

    /**
     * Takes a SEATED customer's order: the customer chooses the item, an Order is created
     * and linked to the customer, and the customer becomes ORDERED.
     */
    public Order takeOrder(Customer customer, Menu menu) throws InvalidActionException {
        // TODO
        return null;
    }

    /**
     * Serves a customer whose order is READY: order and customer become SERVED and servedTurn is recorded.
     */
    public void serve(Customer customer, int turn) throws InvalidActionException {
        // TODO
    }
}
