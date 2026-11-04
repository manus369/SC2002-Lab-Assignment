package rush.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * The restaurant's list of dishes.
 */
public class Menu {

    private List<MenuItem> dishes;

    /**
     * Creates the default dishes in order: Salad (900c, 1u), Burger (1500c, 1u), Pasta (1800c, 2u).
     */
    public Menu() {
        dishes = new ArrayList<MenuItem>();
        // TODO: add Salad, Burger, Pasta in that order
    }

    public List<MenuItem> getDishes() {
        return dishes;
    }

    /**
     * Cyclic lookup: index wraps around the dish list using modulo.
     */
    public MenuItem getDish(int index) {
        // TODO
        return null;
    }
}
