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

        // add numberOfTables tables numbered from 1
        for (int i = 1; i <= numberOfTables; i++) {
            tables.add(new Table(i));
        }
    }

    /**
     * Adds a newly arrived customer to the waiting queue and the list of all customers.
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
        waitingQueue.add(customer);
    }

    /**
     * Finds a customer by id, or throws if there is no such customer.
     */
    public Customer findCustomer(int id) throws InvalidActionException {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }

        throw new InvalidActionException("There is no customer C" + id + ".");
    }

    /**
     * Returns the first free table, or null if all are occupied.
     */
    public Table findFreeTable() {
        for (Table t : tables) {
            if (t.isFree()) {
                return t;
            }
        }

        return null;
    }

    /**
     * Seats a WAITING customer at a free table and removes them from the waiting queue.
     */
    public void seatCustomer(Customer customer) throws InvalidActionException {
        if (customer == null || customer.getStatus() != CustomerStatus.WAITING
                || !waitingQueue.contains(customer)) {
            throw new InvalidActionException("Customer is not in the waiting queue.");
        }

        Table table = findFreeTable();

        if (table == null) {
            throw new InvalidActionException("There is no free table.");
        }

        table.seat(customer);
        customer.setTable(table);
        customer.setStatus(CustomerStatus.SEATED);
        waitingQueue.remove(customer);
    }

    /**
     * Customers SERVED on an earlier turn become READY_TO_PAY and join the readyToPay queue.
     * Returns the customers that changed.
     */
    public List<Customer> finishEating(int turn) {
        List<Customer> finishedCustomers = new ArrayList<Customer>();

        for (Customer c : customers) {
            if (c.getStatus() == CustomerStatus.SERVED
                    && c.getServedTurn() < turn) {
                c.setStatus(CustomerStatus.READY_TO_PAY);
                readyToPay.add(c);
                finishedCustomers.add(c);
            }
        }

        return finishedCustomers;
    }

    /**
     * Returns the oldest customer in the readyToPay queue, or null if none.
     */
    public Customer nextToPay() {
        if (readyToPay.isEmpty()) {
            return null;
        }

        return readyToPay.get(0);
    }

    /**
     * Adds the bill to revenue, records satisfaction, frees the table and removes the customer from readyToPay.
     */
    public void recordPayment(Customer customer) {
        if (!readyToPay.remove(customer)) {
            throw new IllegalStateException("Customer is not ready to pay.");
        }

        revenueCents += customer.getOrder().getPriceCents();
        paidCount++;
        totalPaidSatisfaction += customer.getSatisfaction();

        customer.getTable().free();
        customer.setTable(null);
    }

    /**
     * Customers still waiting for service lose satisfaction; those reaching 0 leave.
     * Returns the customers who left.
     */
    public List<Customer> applyWaitingLoss() {
        List<Customer> leftCustomers = new ArrayList<Customer>();

        for (Customer c : customers) {
            if (c.isWaitingForService()) {
                c.loseSatisfaction();

                if (c.getSatisfaction() == 0) {
                    removeUnhappyCustomer(c);
                    leftCustomers.add(c);
                }
            }
        }

        return leftCustomers;
    }

    /**
     * Marks the customer LEFT, cancels any order, frees their table and removes them from the waiting queue.
     */
    private void removeUnhappyCustomer(Customer c) {
        c.setStatus(CustomerStatus.LEFT);

        if (c.getOrder() != null) {
            c.getOrder().cancel();
        }

        if (c.getTable() != null) {
            c.getTable().free();
            c.setTable(null);
        }

        waitingQueue.remove(c);
        leftCount++;
    }

    public List<Customer> getCustomersWithStatus(CustomerStatus status) {
        List<Customer> matchingCustomers = new ArrayList<Customer>();

        for (Customer c : customers) {
            if (c.getStatus() == status) {
                matchingCustomers.add(c);
            }
        }

        return matchingCustomers;
    }

    /**
     * Customers whose order is READY.
     */
    public List<Customer> getCustomersReadyToServe() {
        List<Customer> readyCustomers = new ArrayList<Customer>();

        for (Customer c : customers) {
            if (c.getOrder() != null
                    && c.getOrder().getStatus() == OrderStatus.READY) {
                readyCustomers.add(c);
            }
        }

        return readyCustomers;
    }

    /**
     * Orders that are still PREPARING.
     */
    public List<Order> getOrdersInKitchen() {
        List<Order> kitchenOrders = new ArrayList<Order>();

        for (Customer c : customers) {
            Order order = c.getOrder();

            if (order != null && order.getStatus() == OrderStatus.PREPARING) {
                kitchenOrders.add(order);
            }
        }

        return kitchenOrders;
    }

    /**
     * Average satisfaction of paid customers, or -1 if no one has paid.
     */
    public double getAverageSatisfaction() {
        if (paidCount == 0) {
            return -1;
        }

        return (double) totalPaidSatisfaction / paidCount;
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
        int count = 0;

        for (Customer c : customers) {
            if (c.getStatus() == CustomerStatus.SERVED
                    || c.getStatus() == CustomerStatus.READY_TO_PAY
                    || c.getStatus() == CustomerStatus.PAID) {
                count++;
            }
        }

        return count;
    }
}
