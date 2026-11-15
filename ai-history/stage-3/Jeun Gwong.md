# Stage 3 — Conversation History

Date: 5 November 2026
AI: ChatGPT

## User

attached are the finalised code for stage 3.
Does Host correctly reuse Staff’s one-task rule while keeping its task independent of the Waiter’s?

## Assistant — progress update

I’ll check how `Host` uses `Staff`’s task limit and whether its task flag is separate from the `Waiter`’s.

## Assistant

**Yes. `Host` correctly reuses `Staff`’s one-task rule, independently of `Waiter`.**

Its task follows the inherited pattern:

```java
checkCanWork();                       // Reject if this Host already worked
restaurant.seatCustomer(customer);
finishTask();                         // Mark this Host as done
```

In your supplied `Staff`, `doneThisTurn` is an **instance field**, not `static`. Each object therefore has its own flag:

| Action | Host flag | Waiter flag |
|---|---|---|
| Turn starts | `false` | `false` |
| Host seats a customer | `true` | `false` |
| Waiter completes a task | `true` | `true` |

A second Host task is rejected without affecting the Waiter’s allowance. If seating throws an exception, `finishTask()` is not reached, so the Host’s task remains available.

**Integration requirement:** the turn controller must call `startTurn()` on both objects each new turn and reuse those objects between turns. I haven’t seen that controller, so its reset handling remains unverified.

## User

review the Stage 2-to-3 diff. Explain which existing rules I reused and whether any changes were unnecessary

## Assistant — progress update

I’ll compare Stage 2 with Stage 3 and trace which existing rules the new code reuses, including any redundant changes.

## Assistant

**Your Stage 3 changes largely reuse the existing design appropriately.** You added `Host` and `HappyHour`, changed `Waiter.takeOrder()`, and left `Order` identical to Stage 2.

| Stage 3 change | Existing rule reused | Assessment |
|---|---|---|
| `Host extends Staff` | `checkCanWork()` and `finishTask()` enforce one successful task per staff object per turn. | Appropriate; Host and Waiter have independent task flags. |
| Host calls `restaurant.seatCustomer(customer)` | Delegates seating to the same method used by Waiter. | Appropriate; avoids implementing separate seating rules. |
| Waiter still calls `customer.applyDiscount(...)` | Customer polymorphism determines the customer-specific discount. | Preserved correctly; no customer-type branches added. |
| Waiter then calls `happyHour.applyDiscount(price)` | Builds on the price already calculated for that customer. | Applies discounts sequentially: customer discount first, then Happy Hour. |
| Waiter passes the final price into `Order` | `Order` already stores the price when the order is taken. | Appropriate; later Happy Hour activation or expiry does not recalculate that stored price. |
| `HappyHour` calls `Money.multiply(...)` | Reuses the existing money calculation helper. | Appropriate; its rounding behaviour depends on that helper. |

The changed ordering path is:

```java
MenuItem item = customer.chooseItem(menu);
int price = customer.applyDiscount(item.getPriceCents());
price = happyHour.applyDiscount(price);
Order order = new Order(customer, item, price);
```

When Happy Hour is inactive, `applyDiscount()` returns the input unchanged, preserving Stage 2’s price calculation.

**Were any changes unnecessary?**

- **`Order(2).java` contains no changes.** Resending it was unnecessary for the diff, but leaving its implementation unchanged was the right choice.
- **Keeping `Waiter.seatCustomer()` is not automatically redundant.** It lets Host seat one customer while Waiter independently seats another or performs another task. Remove it only if the requirements explicitly transfer seating exclusively to Host.
- **Adding `HappyHour` to `takeOrder()` is justified**, but every caller must now supply it. An unchanged Stage 2 call to `takeOrder(customer, menu)` will fail to compile. The supplied object must also be non-null.

**Two advertised Happy Hour rules still depend on integration code:**

| Rule | What the supplied code actually enforces |
|---|---|
| Activate only at the start of a turn | `activate()` checks only whether it was previously used; the controller must enforce timing. |
| Waiting customers recover 15 satisfaction each active turn | `RECOVERY = 15` defines the amount; these files contain no code applying that recovery. |

Likewise, the controller must call `endTurn()` once per turn to make the two-turn duration work. These are unverified integration requirements, rather than unnecessary changes.

