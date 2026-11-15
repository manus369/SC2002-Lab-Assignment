# Stage 1 AI Chat History — Benjamin

**Tool:** Claude Code (VS Code extension)

## What I used AI for in Stage 1

1. **Project skeleton.** I asked Claude to generate a base skeleton for the game: the `rush.boundary`, `rush.control` and `rush.entity` packages with the class files, method signatures and TODO comments, so that each member could fill in their part. This became the first commit in the repository, [`67c95ce` "skeleton"](https://github.com/manus369/SC2002-Lab-Assignment/commit/67c95ce).
2. **Work split.** I asked Claude to suggest how to divide the TODOs between the five members. Its suggested split is reproduced below.

## The work split Claude suggested

> Here's a split by responsibility. Each person owns one cohesive area, so each can explain their part clearly in the video and in their individual reflection. I weighted it by how much logic each TODO needs, not by file count; the enums and the exception are already finished.

| Person | Area | Files | Main work |
|---|---|---|---|
| P1 | Restaurant state | `Restaurant.java`, `Table.java` | Queues, seating, `finishEating`, `applyWaitingLoss` and unhappy departures, payment bookkeeping, the stats and query methods. This is the largest single entity class. |
| P2 | Game flow (control) | `GameController.java`, `Main.java` | Turn loop, arrivals, `waiterStep`/`chefStep` re-prompt loops, victory/defeat check. Also the integrator: owns the reproducible victory and defeat test scripts for the stage-1 tag. |
| P3 | Console UI (boundary) | `ConsoleUI.java` | Input parsing (`readLine` comment stripping and echo, `readInt` validation, end of input), start-of-turn display, staff task list, end-of-turn and result screens. |
| P4 | Staff and orders | `Staff.java`, `Waiter.java`, `Chef.java`, `Cashier.java`, `Order.java` | The one-task-per-turn rule, validation and error messages for every action, the order lifecycle (PREPARING → READY → SERVED → PAID / CANCELLED). |
| P5 | Customers and menu | `Customer.java`, `RegularCustomer.java`, `Menu.java`, `MenuItem.java`, `Money.java`, and ownership of the two enums and `InvalidActionException` | Satisfaction rules, item choice, cent formatting and rounding. These files are small, so P5 also writes the Stage 1 class diagram, which has to match the code. |

> **Order of work.** Everyone depends on P5's `Money`, `Customer` and `Menu`, and on P4's `Order`, so finish those first; they're quick. The integration flows like this: P5 / P4 → P1 → P2, with P3 working in parallel. P3 can build and test the UI against a `Restaurant` with a few hand-added customers. P2 can write the turn loop against the method signatures right away, because the skeleton already compiles.
>
> **Later stages.** If each person keeps their area in Stages 2 and 3, the work stays roughly even:
> - P5: `VIPCustomer`, `CriticCustomer`, `applyDiscount`, `getDishWithMostPrepUnits`, then `ComboMeal`, the combo methods in `Menu`, and the combo choice in `chooseItem`.
> - P4: the S2 discount in `takeOrder`, then `Host` and the `takeOrder(..., HappyHour)` overload.
> - P1: `HappyHour` and `recoverWaitingCustomers`.
> - P2: `createCustomer` for S2, then `happyHourStep` and `hostStep`.
> - P3: displays for the new customer types, Happy Hour turns remaining and combos. P3 also takes the sequence diagram, since it spans the CLI, controller and domain objects.
>
> Everyone writes the "what changed and why" notes for their own files at each stage transition. Those notes feed the README comparisons and the group report.

## Why there is no full transcript

This conversation took place in Claude Code inside VS Code. Claude Code has no option to export a conversation, and the session's transcript is no longer available on my computer, so I cannot provide the full prompts and replies for Stage 1. This file records what the AI was used for and the split it produced.
