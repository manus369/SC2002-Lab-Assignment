package rush.entity;

/**
 * A table that seats at most one customer.
 */
public class Table {

    private int number;
    private Customer customer;

    public Table(int number) {
        this.number = number;
        this.customer = null;
    }

    public int getNumber() {
        return number;
    }

    public Customer getCustomer() {
        return customer;
    }

    /**
     * True when no customer is seated here.
     */
    public boolean isFree() {
        // TODO
        return false;
    }

    /**
     * Seats the customer at this table.
     */
    public void seat(Customer customer) {
        // TODO
    }

    /**
     * Clears the table after the customer pays or leaves.
     */
    public void free() {
        // TODO
    }
}
