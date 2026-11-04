package rush.entity;

/**
 * One customer's order. The price is locked in when the order is taken.
 */
public class Order {

    private Customer customer;
    private MenuItem item;
    private int priceCents;
    private int unitsDone;
    private OrderStatus status;

    public Order(Customer customer, MenuItem item, int priceCents) {
        this.customer = customer;
        this.item = item;
        this.priceCents = priceCents;
        this.unitsDone = 0;
        this.status = OrderStatus.PREPARING;
    }

    /**
     * Adds one preparation unit; the order becomes READY once all of the item's prep units are done.
     */
    public void prepareOneUnit() {
        // TODO
    }

    public void markServed() {
        // TODO
    }

    public void markPaid() {
        // TODO
    }

    /**
     * Cancels the order if it is still PREPARING or READY.
     */
    public void cancel() {
        // TODO
    }

    public Customer getCustomer() {
        return customer;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Progress as "<done>/<total>", e.g. "1/2".
     */
    public String getProgress() {
        // TODO
        return "";
    }

    /**
     * Summary showing item name, formatted price, status and progress.
     */
    @Override
    public String toString() {
        // TODO
        return "";
    }
}
