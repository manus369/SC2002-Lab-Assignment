package rush.entity;

public class HappyHour {

    public static final int DURATION = 2;
    public static final int RECOVERY = 15;

    private boolean used = false;
    private int turnsLeft = 0;

    public boolean isAvailable() {
        return !used;
    }

    public boolean isActive() {
        return turnsLeft > 0;
    }

    public int getTurnsLeft() {
        return turnsLeft;
    }

    public void activate() throws InvalidActionException {
        if (used) {
            throw new InvalidActionException(
                "Happy Hour can only be activated once."
            );
        }
        used = true;
        turnsLeft = DURATION;
    }

    public int applyDiscount(int priceCents) {
        if (isActive()) {
            return Money.multiply(priceCents, 0.8);
        }
        return priceCents;
    }

    public void endTurn() {
        if (turnsLeft > 0) {
            turnsLeft--;
        }
    }
}
