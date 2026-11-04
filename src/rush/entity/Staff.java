package rush.entity;

/**
 * A staff member who can complete at most one task per turn.
 */
public abstract class Staff {

    private String role;
    private boolean doneThisTurn;

    public Staff(String role) {
        this.role = role;
        this.doneThisTurn = false;
    }

    public String getRole() {
        return role;
    }

    /**
     * Resets the staff member so they can act again this turn.
     */
    public void startTurn() {
        // TODO
    }

    public boolean isDoneThisTurn() {
        return doneThisTurn;
    }

    /**
     * Throws if this staff member has already acted this turn.
     */
    protected void checkCanWork() throws InvalidActionException {
        // TODO
    }

    /**
     * Marks this staff member as having acted this turn.
     */
    protected void finishTask() {
        // TODO
    }
}
