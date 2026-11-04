package rush.control;

import rush.boundary.ConsoleUI;
import rush.entity.Cashier;
import rush.entity.Chef;
import rush.entity.Customer;
import rush.entity.InvalidActionException;
import rush.entity.Order;
import rush.entity.RegularCustomer;
import rush.entity.Restaurant;
import rush.entity.Waiter;

/**
 * Runs the turn sequence and applies the manager's choices to the restaurant.
 */
public class GameController {

    public static final int MAX_TURNS = 18;
    public static final int TABLES = 2;
    public static final int[] ARRIVAL_TURNS = {1, 4, 7, 10, 13, 16};
    public static final int TARGET_PAID = 4;
    public static final int TARGET_REVENUE_CENTS = 4500;
    public static final int TARGET_SATISFACTION = 60;

    private Restaurant restaurant;
    private Waiter waiter;
    private Chef chef;
    private Cashier cashier;
    private ConsoleUI ui;
    private int turn;
    private int nextCustomerId;

    public GameController(ConsoleUI ui) {
        this.ui = ui;
        this.restaurant = new Restaurant(TABLES);
        this.waiter = new Waiter();
        this.chef = new Chef();
        this.cashier = new Cashier();
        this.turn = 0;
        this.nextCustomerId = 1;
    }

    /**
     * Shows the welcome screen, plays all MAX_TURNS turns, then shows the result.
     */
    public void play() {
        // TODO
    }

    /**
     * Runs one turn: reset staff, arrivals, show state, Waiter, Chef, Cashier, then end-of-turn updates.
     */
    private void playTurn() {
        // TODO
    }

    private boolean isArrivalTurn(int turn) {
        // TODO
        return false;
    }

    /**
     * Stage 1: every customer is Regular.
     */
    private Customer createCustomer(int id) {
        // TODO
        return null;
    }

    /**
     * Asks for a Waiter action (1=seat, 2=take order, 3=serve, 0=skip) and a customer id
     * (0 goes back to the action menu). Re-prompts on InvalidActionException.
     */
    private void waiterStep() {
        // TODO
    }

    /**
     * Asks for the customer whose order the Chef prepares (0 to skip). Re-prompts on InvalidActionException.
     */
    private void chefStep() {
        // TODO
    }

    /**
     * Checks the three targets and shows Victory or Defeat.
     */
    private void showResult() {
        // TODO
    }
}
