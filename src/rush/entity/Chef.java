package rush.entity;

/**
 * Advances one order by one preparation unit per turn.
 */
public class Chef extends Staff {

    public Chef() {
        super("Chef");
    }

    /**
     * Prepares one unit of a non-null PREPARING order.
     */
    public void prepare(Order order) throws InvalidActionException {
        // TODO
    }
}
