package rush.entity;

/**
 * A restaurant customer. Subclasses define the type name and how fast satisfaction drops.
 */
public abstract class Customer {

    public static final int MAX_SATISFACTION = 100;

    private int id;
    private int arrivalTurn;
    private int satisfaction;
    private CustomerStatus status;
    private Table table;
    private Order order;
    private int servedTurn;

    public Customer(int id, int arrivalTurn) {
        this.id = id;
        this.arrivalTurn = arrivalTurn;
        this.satisfaction = MAX_SATISFACTION;
        this.status = CustomerStatus.WAITING;
        this.table = null;
        this.order = null;
        this.servedTurn = 0;
    }

    public abstract String getType();

    public abstract int getSatisfactionLoss();

    /**
     * Picks the customer's usual dish: menu.getDish(id - 1).
     */
    public MenuItem chooseItem(Menu menu) {
        // TODO
        return null;
    }

    /**
     * True while the customer is still waiting for a table or food (WAITING, SEATED or ORDERED).
     */
    public boolean isWaitingForService() {
        // TODO
        return false;
    }

    /**
     * Reduces satisfaction by getSatisfactionLoss(), never below 0.
     */
    public void loseSatisfaction() {
        // TODO
    }

    public int getId() {
        return id;
    }

    public int getArrivalTurn() {
        return arrivalTurn;
    }

    public int getSatisfaction() {
        return satisfaction;
    }

    public CustomerStatus getStatus() {
        return status;
    }

    public Table getTable() {
        return table;
    }

    public Order getOrder() {
        return order;
    }

    public int getServedTurn() {
        return servedTurn;
    }

    public void setStatus(CustomerStatus status) {
        this.status = status;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setServedTurn(int servedTurn) {
        this.servedTurn = servedTurn;
    }

    /**
     * Identifier and type, e.g. "C1 (Regular)".
     */
    @Override
    public String toString() {
        // TODO
        return "";
    }
}
