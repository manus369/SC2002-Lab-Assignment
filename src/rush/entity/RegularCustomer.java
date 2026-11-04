package rush.entity;

/**
 * Standard customer: loses 8 satisfaction per waiting turn and pays full price.
 */
public class RegularCustomer extends Customer {

    public RegularCustomer(int id, int arrivalTurn) {
        super(id, arrivalTurn);
    }

    @Override
    public String getType() {
        // TODO
        return "";
    }

    @Override
    public int getSatisfactionLoss() {
        // TODO
        return 0;
    }
}
