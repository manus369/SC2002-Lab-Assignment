# Stage 2 Code — Chat History

Date: 4 November 2026
AI: ChatGPT

## User

Does my `takeOrder` method use customer polymorphism correctly? Check whether adding another customer type would require changes to Waiter.

## Assistant

**Yes—your Stage 2 `takeOrder()` uses customer polymorphism correctly**, provided `Customer` declares or inherits these methods:

```java
MenuItem item = customer.chooseItem(menu);
int price = customer.applyDiscount(item.getPriceCents());
```

Although the parameter is declared as `Customer`, Java calls the implementation belonging to the actual customer object. `Waiter` does not need to know its specific type.

For example, adding a student customer could look like:

```java
@Override
public int applyDiscount(int priceCents) {
    return priceCents * 90 / 100;
}
```

**No changes to `Waiter` would be required** if the new type extends `Customer` and follows the same ordering workflow. There are no `instanceof` checks, casts, or customer-type branches in your method.


