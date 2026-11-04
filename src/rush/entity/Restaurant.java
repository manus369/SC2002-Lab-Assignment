package rush.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Holds the menu, tables, customer queues and running totals for one game.
 */
public class Restaurant {

    private Menu menu;
    private List<Table> tables;
    private List<Customer> waitingQueue;
    private List<Customer> readyToPay;
    private List<Customer> customers;
    private int revenueCents;
    private int paidCount;
    private int totalPaidSatisfaction;
    private int leftCount;

    /**
     * Creates the menu and tables numbered 1..numberOfTables.
     */
    public Restaurant(int numberOfTables) {
        menu = new Menu();
        tables = new ArrayList<Table>();
        waitingQueue = new ArrayList<Customer>();
        readyToPay = new ArrayList<Customer>();
        customers = new ArrayList<Customer>();
        revenueCents = 0;
        paidCount = 0;
        totalPaidSatisfaction = 0;
        leftCount = 0;
        // TODO: add numberOfTables tables numbered from 1
    }

    /**
     * Adds a newly arrived customer to the waiting queue and the list of all customers.
     */
    public void addCustomer(Customer customer) {
        // TODO
    }

    /**
     * Finds a customer by id, or throws if there is no such customer.
     */
    public Customer findCustomer(int id) throws InvalidActionException {
        // TODO
        return null;
    }

    /**
     * Returns the first free table, or null if all are occupied.
     */
    public Table findFreeTable() {
        // TODO
        return null;
    }

    /**
     * Seats a WAITING customer at a free table and removes them from the waiting queue.
     */
    public void seatCustomer(Customer customer) throws InvalidActionException {
        // TODO
    }

    /**
     * Customers SERVED on an earlier turn become READY_TO_PAY and join the readyToPay queue.
     * Returns the customers that changed.
     */
    public List<Customer> finishEating(int turn) {
        // TODO
        return new ArrayList<Customer>();
    }

    /**
     * Returns the oldest customer in the readyToPay queue, or null if none.
     */
    public Customer nextToPay() {
        // TODO
        return null;
    }

    /**
     * Adds the bill to revenue, records satisfaction, frees the table and removes the customer from readyToPay.
     */
    public void recordPayment(Customer customer) {
        // TODO
    }

    /**
     * Customers still waiting for service lose satisfaction; those reaching 0 leave.
     * Returns the customers who left.
     */
    public List<Customer> applyWaitingLoss() {
        // TODO
        return new ArrayList<Customer>();
    }

    /**
     * Marks the customer LEFT, cancels any order, frees their table and removes them from the waiting queue.
     */
    private void removeUnhappyCustomer(Customer c) {
        // TODO
    }

    public List<Customer> getCustomersWithStatus(CustomerStatus status) {
        // TODO
        return new ArrayList<Customer>();
    }

    /**
     * Customers whose order is READY.
     */
    public List<Customer> getCustomersReadyToServe() {
        // TODO
        return new ArrayList<Customer>();
    }

    /**
     * Orders that are still PREPARING.
     */
    public List<Order> getOrdersInKitchen() {
        // TODO
        return new ArrayList<Order>();
    }

    /**
     * Average satisfaction of paid customers, or -1 if no one has paid.
     */
    public double getAverageSatisfaction() {
        // TODO
        return -1;
    }

    public Menu getMenu() {
        return menu;
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Customer> getWaitingQueue() {
        return waitingQueue;
    }

    public int getRevenueCents() {
        return revenueCents;
    }

    public int getPaidCount() {
        return paidCount;
    }

    public int getLeftCount() {
        return leftCount;
    }

    /**
     * Number of customers who have been served so far.
     */
    public int getServedCount() {
        // TODO
        return 0;
    }
}
