package rush.entity;

/**
 * Thrown when the manager picks an action that breaks a game rule.
 * The message explains why, and the staff member's task is not used up.
 */
public class InvalidActionException extends Exception {

    public InvalidActionException(String message) {
        super(message);
    }
}
