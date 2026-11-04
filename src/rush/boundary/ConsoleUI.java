package rush.boundary;

import java.util.List;
import java.util.Scanner;

import rush.entity.Customer;
import rush.entity.Order;
import rush.entity.Restaurant;

/**
 * Console input and output. Collects the manager's decisions and displays state; holds no game rules.
 */
public class ConsoleUI {

    private Scanner scanner;
    private boolean echo;

    public ConsoleUI(boolean echo) {
        this.scanner = new Scanner(System.in);
        this.echo = echo;
    }

    /**
     * Shows the game intro, menu, staff and targets.
     */
    public void showWelcome(Restaurant restaurant, int maxTurns) {
        // TODO
    }

    public void showTurnHeader(int turn, int maxTurns) {
        // TODO
    }

    /**
     * Shows revenue, average satisfaction, waiting queue, tables, customer satisfaction,
     * order status and available staff tasks.
     */
    public void showTurnState(Restaurant restaurant) {
        // TODO
    }

    /**
     * Lists which customers each staff member can currently act on.
     */
    private void showStaffTasks(Restaurant restaurant) {
        // TODO
    }

    private String listCustomers(List<Customer> customers) {
        // TODO
        return "";
    }

    private String listOrders(List<Order> orders) {
        // TODO
        return "";
    }

    public void showMessage(String message) {
        // TODO
    }

    public void showError(String message) {
        // TODO
    }

    /**
     * Reads an integer in [min, max], re-prompting on bad input.
     */
    public int readInt(String prompt, int min, int max) {
        // TODO
        return 0;
    }

    /**
     * Reads a y/n answer, re-prompting on bad input.
     */
    public boolean readYesNo(String prompt) {
        // TODO
        return false;
    }

    /**
     * Reads the next line, skipping lines starting with '#', stripping inline '#' comments,
     * and echoing the line if echo is on.
     */
    private String readLine() {
        // TODO
        return "";
    }

    /**
     * Shows served count, unhappy departures, revenue, average satisfaction and turns used.
     */
    public void showEndOfTurn(int turn, int maxTurns, Restaurant restaurant) {
        // TODO
    }

    /**
     * Shows Victory or Defeat with one line per target.
     */
    public void showResult(boolean victory, Restaurant restaurant, String[] targetLines) {
        // TODO
    }

    /**
     * Formats an average to one decimal place, or "N/A" when it is negative (no one has paid).
     */
    public String formatAverage(double average) {
        // TODO
        return "";
    }
}
