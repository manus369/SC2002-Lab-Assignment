package rush.entity;

/**
 * A dish on the menu with a price and the number of Chef preparation units it needs.
 */
public class MenuItem {

    private String name;
    private int priceCents;
    private int prepUnits;

    public MenuItem(String name, int priceCents, int prepUnits) {
        this.name = name;
        this.priceCents = priceCents;
        this.prepUnits = prepUnits;
    }

    public String getName() {
        return name;
    }

    public int getPriceCents() {
        return priceCents;
    }

    public int getPrepUnits() {
        return prepUnits;
    }

    /**
     * Summary of the dish, e.g. "Pasta $18.00 (2 units)".
     */
    @Override
    public String toString() {
        // TODO
        return "";
    }
}
