# Stage 2 — Compatibility and test checks

## User

Do i not need to make any changes for stage 2? Do I only test the game?

## Assistant

**Yes, that's correct. For your assigned P1 role, you most likely don't need to modify `Restaurant.java` or `Table.java` for Stage 2.** Your main responsibility is to verify that your Stage 1 implementation works with the new VIP and Critic customers.

I checked the Stage 2 reference and your current `Restaurant.java`. Your existing implementation already supports the new satisfaction rules through polymorphism.

### Why don't you need to change the code?

Look at your existing method:

```java
public List<Customer> applyWaitingLoss() {
    List<Customer> leftCustomers = new ArrayList<Customer>();

    for (Customer c : customers) {
        if (c.isWaitingForService()) {
            c.loseSatisfaction();

            if (c.getSatisfaction() == 0) {
                removeUnhappyCustomer(c);
                leftCustomers.add(c);
            }
        }
    }

    return leftCustomers;
}
```

The important line is:

```java
c.loseSatisfaction();
```

Inside `Customer.java`, this calls:

```java
satisfaction = Math.max(0, satisfaction - getSatisfactionLoss());
```

Because `getSatisfactionLoss()` is overridden by customer subclasses, each type can lose a different amount without you changing `Restaurant.java`.

### What you should do for Stage 2

Once your teammates have implemented the Stage 2 features, test these scenarios:

| Test | Expected result |
|---|---|
| Regular customer waits 1 turn | Satisfaction becomes 92 |
| VIP customer waits 1 turn | Satisfaction becomes 95 |
| Critic customer waits 1 turn | Satisfaction becomes 88 |
| VIP customer pays | Restaurant records the discounted bill |
| Critic customer orders | Chooses the dish with the most preparation units |
| Customer reaches 0 satisfaction | Leaves, unfinished order cancelled, table freed |
| Customer pays | Revenue and paid count increase, table freed |

Your main focus should be the restaurant-state operations: satisfaction, queues, seating, payment and departures. The Critic's food choice and VIP discount implementation belong to your teammates, but you should check that they integrate correctly.

### What about contributing to Stage 2?

You can contribute **test evidence** even if no production-code modifications are needed.

For example, document the initial and final satisfaction values for all three customer types, and verify the restaurant totals after payments.

That gives you something concrete to explain in your video or reflection:

> For Stage 2, I verified that the existing Restaurant class supports the new customer types without requiring changes to its core logic. Since satisfaction loss is handled through polymorphism in the Customer subclasses, the Restaurant class can process Regular, VIP and Critic customers using the same methods. I also tested that seating, payments and customer departures continued to work correctly.

Only use that wording after you've performed the tests.

**My recommendation:** Don't modify working code just to have another commit. Stage 2 is a good example of how your Stage 1 OOP design supports extension without modification, which is relevant to your assignment's design assessment.

Your next substantial coding task as P1 will be **Stage 3: implementing `HappyHour.java` and `recoverWaitingCustomers()` in `Restaurant.java`**.

