# Stage 3 — Happy Hour code review

## User

```java
package rush.entity;

import java.util.ArrayList;
import java.util.List;

/** Holds the tables, the waiting queue, the customers and the results so far. */
public class Restaurant {

    private Menu menu = new Menu();
    private HappyHour happyHour = new HappyHour();
    private List<Table> tables = new ArrayList<>();
    private List<Customer> waitingQueue = new ArrayList<>();
    private List<Customer> readyToPay = new ArrayList<>(); // oldest first
    private List<Customer> customers = new ArrayList<>();  // everyone who arrived

    private int revenueCents = 0;
    private int paidCount = 0;
    private int totalPaidSatisfaction = 0;
    private int leftCount = 0;

    public Restaurant(int numberOfTables) {
        for (int i = 1; i <= numberOfTables; i++) {
            tables.add(new Table(i));
        }
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
        waitingQueue.add(customer);
    }

    public Customer findCustomer(int id) throws InvalidActionException {
        for (Customer c : customers) {
            if (c.getId() == id) {
                return c;
            }
        }
        throw new InvalidActionException("There is no customer C" + id + ".");
    }

    public Table findFreeTable() {
        for (Table t : tables) {
            if (t.isFree()) {
                return t;
            }
        }
        return null;
    }

    /** Moves a queued customer to a free table. Used by the Waiter (and later the Host). */
    public void seatCustomer(Customer customer) throws InvalidActionException {
        if (customer.getStatus() != CustomerStatus.WAITING) {
            throw new InvalidActionException(customer + " is not in the waiting queue.");
        }
        Table table = findFreeTable();
        if (table == null) {
            throw new InvalidActionException("There is no free table.");
        }
        table.seat(customer);
        customer.setTable(table);
        customer.setStatus(CustomerStatus.SEATED);
        waitingQueue.remove(customer);
    }

    /** Customers served on an earlier turn finish eating. Returns those who are now READY_TO_PAY. */
    public List<Customer> finishEating(int turn) {
        List<Customer> finished = new ArrayList<>();
        for (Customer c : customers) {
            if (c.getStatus() == CustomerStatus.SERVED && c.getServedTurn() < turn) {
                c.setStatus(CustomerStatus.READY_TO_PAY);
                readyToPay.add(c);
                finished.add(c);
            }
        }
        return finished;
    }

    public Customer nextToPay() {
        if (readyToPay.isEmpty()) {
            return null;
        }
        return readyToPay.get(0);
    }

    public void recordPayment(Customer customer) {
        readyToPay.remove(customer);
        revenueCents += customer.getOrder().getPriceCents();
        paidCount++;
        totalPaidSatisfaction += customer.getSatisfaction();
        customer.getTable().free();
    }

    public List<Customer> recoverWaitingCustomers(int amount) {
        List<Customer> recoveredCustomers = new ArrayList<Customer>();

        for (Customer c : customers) {
            if (c.isWaitingForService()) {
                c.recover(amount);
                recoveredCustomers.add(c);
            }
        }

        return recoveredCustomers;
    }

    /** End of turn: unserved customers lose satisfaction. Returns those who reached 0 and left. */
    public List<Customer> applyWaitingLoss() {
        List<Customer> left = new ArrayList<>();
        for (Customer c : customers) {
            if (c.isWaitingForService()) {
                c.loseSatisfaction();
                if (c.getSatisfaction() == 0) {
                    removeUnhappyCustomer(c);
                    left.add(c);
                }
            }
        }
        return left;
    }

    private void removeUnhappyCustomer(Customer c) {
        c.setStatus(CustomerStatus.LEFT);
        if (c.getOrder() != null) {
            c.getOrder().cancel();
        }
        if (c.getTable() != null) {
            c.getTable().free();
        }
        waitingQueue.remove(c);
        leftCount++;
    }

    public List<Customer> getCustomersWithStatus(CustomerStatus status) {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customers) {
            if (c.getStatus() == status) {
                result.add(c);
            }
        }
        return result;
    }

    /** Customers whose order is READY and can be served by the Waiter. */
    public List<Customer> getCustomersReadyToServe() {
        List<Customer> result = new ArrayList<>();
        for (Customer c : customers) {
            if (c.getOrder() != null && c.getOrder().getStatus() == OrderStatus.READY) {
                result.add(c);
            }
        }
        return result;
    }

    /** Orders that the Chef can still work on. */
    public List<Order> getOrdersInKitchen() {
        List<Order> result = new ArrayList<>();
        for (Customer c : customers) {
            if (c.getOrder() != null && c.getOrder().getStatus() == OrderStatus.PREPARING) {
                result.add(c.getOrder());
            }
        }
        return result;
    }

    /** Average satisfaction of paid customers, or -1 if nobody has paid yet. */
    public double getAverageSatisfaction() {
        if (paidCount == 0) {
            return -1;
        }
        return (double) totalPaidSatisfaction / paidCount;
    }

    public Menu getMenu() {
        return menu;
    }

    public HappyHour getHappyHour() {
        return happyHour;
    }

    public List<Table> getTables() {
        return tables;
    }

    public List<Customer> getWaitingQueue() {
        return waitingQueue;
    }

    public int getRevenueCents() {
        return revenueCents;
    }

    public int getPaidCount() {
        return paidCount;
    }

    /** Customers who have been served their food (including those who have paid since). */
    public int getServedCount() {
        int count = 0;
        for (Customer c : customers) {
            CustomerStatus s = c.getStatus();
            if (s == CustomerStatus.SERVED || s == CustomerStatus.READY_TO_PAY || s == CustomerStatus.PAID) {
                count++;
            }
        }
        return count;
    }

    public int getLeftCount() {
        return leftCount;
    }
}
```

```java
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
```

As my groupmates dont seem to have pushed their stage 3 stuff yet, I have just coded this on my end first, should I wait for them to add their stuff before pushing mine?

## Assistant

**I recommend keeping your Stage 3 changes locally for now, rather than pushing them directly to `main`.** However, you don't need to wait for every teammate to finish before integrating your code.

I've reviewed both of your files, and your Stage 3 implementation looks largely correct. There is just one important dependency to resolve.

### 1. What I've checked

| Component | Status | Notes |
|---|---|---|
| `HappyHour.java` | Looks correct | Activation, duration and discount logic |
| `Restaurant.java` | Looks correct | Recovery targets customers still waiting for service |
| `Money.multiply()` | Available | Supports the 20% discount |
| `Customer.recover()` | Missing | Needed before your updated `Restaurant.java` can compile |

I checked your group's current `Customer.java` on GitHub. It doesn't currently contain `recover(int amount)`, which your new method calls. The existing `Money.java` does support `multiply(int, double)`.

That means your Stage 3 code will not compile against the currently published classes until the customer recovery method is added.

Since Ben owns `Customer.java`, coordinate that addition with him rather than modifying his file without agreement.

### 2. Save your work on a local branch

Even though Benjamin wants everyone to push directly to `main`, you can still use a local branch to preserve unfinished work.

Since you've already edited the files, run these commands in your VS Code terminal:

```powershell
git status
git switch -c joel-stage3
```

This creates a new branch and carries your current uncommitted changes with it.

Then save your work:

```powershell
git add src/rush/entity/HappyHour.java src/rush/entity/Restaurant.java
git commit -m "Implement Happy Hour and satisfaction recovery"
```

Your code is now committed locally without affecting the shared `main` branch.

### 3. What needs to happen before you push?

You should coordinate these dependencies:

- **Ben (P5):** Add `recover(int amount)` to `Customer.java`, ensuring satisfaction never exceeds 100.
- **JY (P2):** Call the recovery method at the start of active Happy Hour turns and `endTurn()` at the end of each turn.
- **Bob (P4):** Apply the Happy Hour discount when an order is taken, after the VIP discount.

Once the relevant methods have been integrated, compile and test that Happy Hour lasts exactly two turns, cannot be activated twice, and correctly restores satisfaction.

You don't need to wait for all the UI and menu work to finish if your code is already compatible and the shared project still compiles.

### 4. Push to main when integration is ready

After your teammates have added the required dependencies, run:

```powershell
git switch main
git pull --ff-only origin main
git merge joel-stage3
```

Resolve any merge conflicts if Git reports them.

Then compile:

```powershell
javac -d out (Get-ChildItem .\src -Recurse -Filter *.java | ForEach-Object { $_.FullName })
```

If compilation and your relevant tests pass, upload directly:

```powershell
git push origin main
```

Also make sure your team has tagged the runnable Stage 2 version before adding Stage 3 code to the shared `main`.

**My recommendation:** Your code is ready to preserve in a local commit, but not yet ready to push into the shared `main` because `Customer.recover()` is missing.

You can tell Ben that your `Restaurant.java` now calls `c.recover(amount)` and that you need his `Customer.java` implementation to support this. Once that dependency is resolved, you can test and push your work without waiting for every Stage 3 feature to be completed.
