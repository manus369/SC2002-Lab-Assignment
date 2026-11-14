# Restaurant Rush — video script

**Format:** five solo segments, one per member, with no handing back and forth. After them come five individual reflections, each recorded by its owner.
We show one overview slide, then the terminal demo, then we zoom into our own UML diagrams for the design segments.
The professor wants the focus on **design, OODP and SOLID, and the change map per stage**, so four of the five segments are about the design.

| Part | Segment | Speaker | Length | Required video segments covered |
|---|---|---|---|---|
| 1 | Overview slide + gameplay demo | **Benjamin** | 4:00 | 1 launch · 2 manager choices + staff constraint · 3 READY→SERVED→paid · 4 VIP/Critic · 5 Stage 3 feature · 6 victory and defeat |
| 2 | Architecture and the Stage 1 design | **Joel** | 2:30 | 7 diagrams |
| 3 | Stage 1 → 2: polymorphism and change map 1 | **Jeun Gwong** | 2:00 | 7 tag comparison + diagrams |
| 4 | Stage 2 → 3: final design and change map 2 | **Eswaran** | 2:15 | 7 tag comparison + diagrams |
| 5 | Use case and sequence diagram | **Jun Yu** | 2:00 | — (sequence diagram) |
| 6 | Individual reflections (5 × about 40 s) | each member | 3:20 | — |
| | **Total** | | **≈ 16:00** | |

**Who should take A–D:** whoever built those classes explains them best.
- **A:** Restaurant/Table owner, or Staff/Order owner. The segment covers both.
- **B:** Customer/Menu owner.
- **C:** owner of HappyHour, Host or ComboMeal. This is the strongest SOLID segment.
- **D:** ConsoleUI owner, who also drew the sequence diagram in our original split.

If the total runs over 15 minutes, trim inside the design segments. Don't cut the reflections.

---

## Setup (every speaker)

Each person can record their own segment on their own laptop, and we stitch the clips together in order.

- **Parts 2–5 only need a browser.** Open your diagram's **.svg** file in Chrome. Get it with `git pull`, or download it from the repo's `docs/diagrams/` folder, then drag the file into Chrome.
  - Part 2: `stage1-class.svg`
  - Part 3: `stage1-class.svg`, then `stage2-class.svg`
  - Part 4: `stage3-class.svg`
  - Part 5: `stage3-sequence.svg`
- SVGs stay sharp at any zoom. **Ctrl + scroll** zooms in and out; **Ctrl+0** fits the whole diagram on screen.
- **Change-map numbers** are printed in the legend box at the bottom right of the Stage 2 and Stage 3 diagrams. Zoom into the legend when you say them.
- **Zooming:** say the class name, zoom until one or two boxes fill the screen, keep the mouse still while you talk, then press Ctrl+0 before moving on. Move slowly.
- **Clip length:** aim for the length in the table.

---

## Part 1 — Overview and gameplay demo (Benjamin, 4:00)

**Extra setup for this segment:**
- **Opening visual:** `docs/overview-slide.png`, a 1920×1080 overview slide. Open it full screen: in the Windows Photos app press **F11**, or open `docs/overview-slide.html` in Chrome and press **F11**. If you change any text, edit the `.html` file.
- **Compile and make the replay logs** in PowerShell from the project folder:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java).FullName
Get-Content runs\stage3-victory.txt | java -cp out rush.Main --echo | Out-File -Encoding utf8 out\victory-log.txt
Get-Content runs\stage3-defeat.txt | java -cp out rush.Main --echo | Out-File -Encoding utf8 out\defeat-log.txt
code out\victory-log.txt out\defeat-log.txt
```

### 0:00 — Overview (overview-slide.png, ~45 s)

Point to each part of the slide as you mention it: the title and the one-task-per-turn line, the menu chips, the three dark tiles, the orange win tile, then the three stage cards from left to right. After the last sentence, Alt+Tab to the terminal.

> Hi, we're [group name] from [lab group], and this is **Restaurant Rush**, a turn-based restaurant game played in the console. You're the manager, and the whole game is about limited staff time: every staff member can do only one thing per turn.
>
> The base setting is fixed so anyone can reproduce our runs: 18 turns, 2 tables, and six customers arriving on turns 1, 4, 7, 10, 13 and 16. Salad is nine dollars, Burger fifteen, and Pasta eighteen, which takes two preparation units. To win, by the end of turn 18 you need four paying customers, forty-five dollars in revenue, and an average satisfaction of sixty among the customers who paid.
>
> We built it in three tagged stages. Stage 1 is the core game with a Waiter, a Chef and a Cashier. Stage 2 adds VIP and Critic customers. Stage 3 adds a Host, combo meals and Happy Hour. I'll show it running, and then my teammates will walk through the design.

### 0:45 — Launch (terminal, ~30 s) · *segment 1*

```powershell
git tag
java -cp out rush.Main
```

> These are our three runnable stage tags: stage-1, stage-2 and stage-3. This is the final version, compiled from the source folder and launched with no IDE and no libraries. Each turn opens with the full state: revenue, average satisfaction, the queue, both tables with each customer's satisfaction and order status, and the tasks each staff member can do right now.

### 1:15 — Manager choices and invalid input (same game, ~45 s) · *segment 2*

Type these one at a time, saying what you're doing:

| Prompt | Type | What appears |
|---|---|---|
| Start Happy Hour now? | `n` | |
| Host: seat which queued customer? | `1` | Host seats C1 at Table 1 |
| Waiter task | `3`, then `1` | "Not allowed: C1 has no READY order to serve. Please choose again." |
| Waiter task | `abc` | "Please enter a number from 0 to 3." |
| Waiter task | `2`, then `1` | C1 orders the **Salad + Burger Combo for $21.60** |
| Chef | `9` | "Not allowed: There is no customer C9." |
| Chef | `1` | Chef prepares C1's combo (1/2) |

Then press **Ctrl+C**.

> Every turn I decide whether to start Happy Hour, who the Host seats, what the Waiter does and what the Chef cooks. If I try to serve food that isn't ready, the game tells me why and asks again, and the Waiter doesn't lose the turn. Typos and customers that don't exist are handled the same way. Notice that the customer chose the combo, not me: customers at ninety satisfaction or more upgrade to a combo.

### 2:00 — One order from start to finish (VS Code, victory-log.txt, ~1:30) · *segments 2, 3, 4, 5*

> Playing 18 turns live would take too long, so this is a replay of our saved script, stage3-victory.

Ctrl+F `TURN 7`, then scroll slowly through turns 7–12, pointing at each line as you mention it.

> **Turn 7.** C3, a **VIP**, arrives. I switch on **Happy Hour**, which lasts this turn and the next. The **Host** seats C3, so the Waiter can take the order in the same turn. C3 picks the Salad and Pasta **combo**: twenty-four thirty, minus ten percent for the VIP, then twenty percent for Happy Hour, which comes to **seventeen fifty**, locked into the order. You can also see C2, a **Critic**: Critics lose twelve satisfaction a turn and always order the slowest dish, Pasta.
>
> **Turns 8 and 9.** The Chef finishes the three preparation units, and the order is READY at the end of turn 9.
>
> **Turn 10. This is the staff constraint.** C4 arrives, but the Waiter can do only one thing: serve C3 or take C4's order. I serve C3, so C3 stops losing satisfaction. Taking C4's order first would have cost C3 five more points and delayed the payment.
>
> **Turn 11.** C3 finishes eating and is ready to pay. **Turn 12.** The Cashier collects seventeen fifty at satisfaction ninety and frees the table. That's READY, SERVED, eaten, paid.

### 3:30 — Victory and defeat (VS Code, ~30 s) · *segment 6*

Ctrl+F `VICTORY` in victory-log.txt, then open defeat-log.txt and Ctrl+F `DEFEAT`.

> That run ends in **victory**: five paid customers, a hundred dollars thirty, and an average of eighty point four. This separate run, stage3-defeat, makes worse choices. Happy Hour is wasted on turn one, and the Critic is never seated, so they walk out. That's a **defeat**, with only one paying customer. All our runs, including a Stage 1 victory and defeat, are in the runs folder with their results in the README. Now [Member A] will explain how the code is designed.

---

## Part 2 — Architecture and the Stage 1 design (Member A, 2:30) · *segment 7*

Open **stage1-class.svg**, fully zoomed out.

> This is our Stage 1 class diagram, matching the code at the stage-1 tag. We split the classes into three packages: **boundary**, **control** and **entity**.

Zoom into **ConsoleUI** and **GameController** (top left).

> ConsoleUI is the boundary: it only reads input and prints the state. GameController is the control: it runs each turn's steps in a fixed order and loops until every choice is valid. Neither one contains a game rule. That's the **single responsibility principle** at the package level.

Ctrl+0, then zoom into **Restaurant**, **Table** and **Menu**.

> The rules live in the entities. Restaurant holds the tables, the waiting queue, the ready-to-pay queue and the running totals. The filled diamonds are **composition**: Restaurant creates its Tables and its Menu, and they live and die with it. Seating, finishing eating, recording payment and the end-of-turn satisfaction loss are all Restaurant methods, so that state only changes in one place.

Ctrl+0, then zoom into **Staff** and its subclasses, then the encapsulation note.

> Staff is abstract, and the rule that a staff member does only one task per turn lives here, once. The flag `doneThisTurn` is **private**. Subclasses have to call the protected `checkCanWork` before acting and `finishTask` after succeeding. That's **encapsulation**: no Waiter or Chef can act twice, and if an action throws an exception, `finishTask` never runs, so a failed choice doesn't use up the turn. In Stage 3 the Host got this rule for free.

Ctrl+0, then zoom into **Order** and **OrderStatus**.

> Order owns its own lifecycle: PREPARING, READY, SERVED, PAID, or CANCELLED if the customer leaves. Its price is set once in the constructor with no setter. That's how the price gets locked when the order is taken, which Happy Hour relies on later. All money is stored in whole cents to avoid rounding errors. Next, [Member B] will show how this design handled Stage 2.

---

## Part 3 — Stage 1 → 2: polymorphism and change map 1 (Member B, 2:00) · *segment 7*

In **stage1-class.svg**, zoom into **Customer** and **RegularCustomer**.

> Even in Stage 1, with only one customer type, we made Customer **abstract**: `getSatisfactionLoss` and `getType` are abstract, and `chooseItem` can be overridden. Restaurant only calls `loseSatisfaction` on a Customer reference. It never asks what kind of customer it has.

Open **stage2-class.svg**. Zoom into **VIPCustomer** and **CriticCustomer** (green), then **Customer** (yellow).

> That made Stage 2 cheap. Green means new, yellow means changed. VIP and Critic are two new subclasses. VIP overrides the loss to five and gives ten percent off. Critic overrides the loss to twelve and overrides `chooseItem` to pick the dish with the most preparation units. The only change to Customer was one new method, `applyDiscount`, which charges full price by default.
>
> This is **polymorphism**: the Waiter calls `customer.applyDiscount(price)`, and each object decides for itself. There is no if-statement on customer type in the controller. It's also **open/closed**, because we added behaviour by adding classes, and **Liskov substitution**, because a VIP or a Critic works anywhere a Customer does.

Ctrl+0 so the whole Stage 2 diagram shows: just two green boxes and a few yellow ones. Then zoom into the **legend box** (bottom right), which shows the change map.

> Here is the change map from Stage 1 to Stage 2, in the legend. We went from 18 classes to 20. **Two added**: VIP and Critic. **Ten modified**, and we'll be honest about that number. Only five, the yellow ones, were needed for the feature: Customer, Menu, Waiter, the controller's customer line-up and the UI. The other five were comment and formatting cleanup with no change in behaviour, done at the same time. Next time we'd keep cleanup separate. **Eight unchanged**, including Order, Staff, Chef and Cashier, which are all still white on the diagram. [Member C] will take it from Stage 2 to Stage 3.

---

## Part 4 — Stage 2 → 3: final design and change map 2 (Member C, 2:15) · *segment 7*

Open **stage3-class.svg**, fully zoomed out.

> This is the final design at the stage-3 tag, with 23 classes. The three green classes are new.

Zoom into **Host**.

> **Host** extends Staff, so it inherits the one-task rule, and it reuses Restaurant's existing `seatCustomer`. The seating rules aren't written twice.

Zoom into **ComboMeal**, **MenuItem** and the "Composition + LSP" note.

> **ComboMeal** is a MenuItem made of two other MenuItems, so it's inheritance and composition together. Its constructor works out the price, ninety percent of the two, and the preparation units, the sum of the two. Because a combo *is a* MenuItem, Order, Chef and the display treat it like any single dish, and none of them changed. That's **Liskov substitution** doing real work.

Zoom into **HappyHour** and its link to Restaurant.

> **HappyHour** is its own object, owned by Restaurant. It keeps track of whether it has been used, how many turns are left, and its twenty percent discount. The Waiter applies the customer's discount first, then Happy Hour's, in one place.

Zoom into the **legend box** (bottom right), which shows the change map.

> The legend shows the change map from Stage 2 to Stage 3. 20 classes became 23. **Three added**: Host, HappyHour and ComboMeal. **Eight modified**, six of them for real, shown in yellow: the controller registers the Host and the Happy Hour step, the UI displays them, Customer gets the combo rule, Menu holds the combos, Restaurant owns HappyHour, and the Waiter uses it when pricing. The other two were a blank line and a comment. **Twelve unchanged**.

Ctrl+0, then zoom into **Order**, **Chef** and **Cashier**, which are still white.

> And this is our strongest evidence, listed on the legend too: Order, Chef, Cashier, Staff, MenuItem, Money and RegularCustomer have not changed at all from Stage 1 to Stage 3. The order lifecycle, the kitchen and payment survived two rounds of new requirements untouched.
>
> We're honest about where SOLID is weaker. We used **no interfaces**, on purpose, because the brief warned against adding them just for a checklist. Dependency inversion is only partial: the controller works with the abstract Customer and Staff, but it creates the concrete classes itself. The alternative we considered was a CustomerFactory and a pricing-strategy chain. We kept simple hook methods because there are only two pricing rules. [Member D] will now trace one use case through the code.

---

## Part 5 — Use case and sequence diagram (Member D, 2:00)

Open **stage3-sequence.svg**. Show the whole height for a second, then zoom into the **title and starting-conditions note**.

> Our sequence diagram is a Stage 3 use case: a VIP orders a combo during Happy Hour, then gets served, eats and pays. It starts at turn 7 of the victory run: C1 has paid, the Critic is eating at table 2, table 1 is free and Happy Hour hasn't been used. It ends with C3 paying seventeen fifty at satisfaction ninety, and the game ends in victory.

Zoom into **opt isArrivalTurn** and **alt manager switches Happy Hour on**.

> The controller creates the VIP and adds it to the Restaurant. It asks the manager, through ConsoleUI, whether to start Happy Hour, and the alt shows both answers. While Happy Hour is active, a loop lets each waiting customer recover fifteen satisfaction.

Scroll to the **Host step loop**.

> The Host step is a loop. If the choice is invalid, Restaurant throws InvalidActionException, ConsoleUI shows the reason, and we ask again without using up the task.

Scroll to the **Waiter step**: `chooseItem`, **alt satisfaction ≥ 90**, the two `applyDiscount` calls, then `create Order`.

> The Waiter asks the customer object to choose. The customer asks the Menu for its usual dish and the matching combo, and the alt picks the combo because satisfaction is at least ninety. Pricing is two polymorphic calls: the VIP's discount takes twenty-four thirty to twenty-one eighty-seven, and Happy Hour takes that to seventeen fifty. Creating the Order with that price locks it. At the end of the turn, `getSatisfactionLoss` returns five, because the VIP overrides it.

Scroll through **Turns 8–9**, **Turn 10**, **Turn 11**, **Turn 12**, then **After turn 18**.

> A loop covers the two kitchen turns until the order is READY. Turn 10 is the manager's choice under the staff constraint, and the alt is the serve guard: serving anything that isn't READY throws an exception. On turn 11 C3 becomes ready to pay. On turn 12 the Cashier marks the order paid, Restaurant adds the revenue and the table is freed. After turn 18, the controller checks the three targets, and the final alt shows victory or defeat.
>
> That's our design. Each of us will now reflect on our own part.

---

## Part 6 — Individual reflections (each member records their own, about 40 s each)

Record yourself, on camera if you can, either over the overview slide (`docs/overview-slide.png`) or over the diagram of your own classes. Order in the video: Benjamin, A, B, C, D.

Cover the brief's four questions about **your own code**: **what I learned → how I applied it → what challenged me → how I solved it.**

The drafts below are arranged by area of the code and built from what the code actually does. **Pick the one for the classes you built and rewrite it in your own words.** Replace anything that didn't happen to you; the "my first version" details in particular are only examples of the shape. Markers can tell a real reflection from a generic one. Use the same story, with more detail, in your written reflection in the report.

**Restaurant state (Restaurant, Table, HappyHour recovery)**
> The main thing I learned was what encapsulation really protects: the restaurant's state should only change in one place. I applied that by putting every status change in Restaurant methods: seating, finishing eating, recording a payment and an unhappy customer leaving. In Stage 3 we went further, making the fields final and having the getters return read-only lists.
> What challenged me most was the timing rules. A customer served this turn can't finish eating until next turn, and a customer who becomes ready to pay this turn can't pay until next turn. [My first version let both happen in the same turn.] I solved it by tracing the brief's one-customer example turn by turn (92, 84, 76, then paying on turn 6). The fix was running the Cashier before finishEating and only finishing customers whose served turn is earlier than the current turn. I learned to write the expected numbers down first and then make the code match.

**Game flow and integration (GameController, Main, run scripts)**
> I learned what a control class is for: it decides the order things happen in, but holds no rules itself. Each staff step is a loop that catches InvalidActionException, shows the reason and asks again. Because of that, Stage 3 was just two new steps, happyHourStep and hostStep, added to a turn loop that otherwise stayed the same.
> My challenge was proving the game works in a way anyone can repeat. [Playing 18 turns by hand gave different results each time, and merging five branches kept breaking the build.] I solved it with input scripts that have a comment for each turn, plus an echo flag that makes the replay readable. We replayed every script on its own stage tag, and the Stage 1 victory and defeat runs became our test notes. I learned that reproducibility has to be designed in.

**Console UI (ConsoleUI)**
> I learned to keep the boundary thin: the UI only reads and prints, and every "not allowed" message comes from an exception thrown by an entity. That meant my code never had to change when a game rule changed.
> The hardest part was making input robust. Players type letters, numbers out of range and customers who don't exist, and the replay scripts needed comments and a clean stop when the input ends. I solved it with one readInt loop that validates the range, plus a readLine that strips comments and stops the game cleanly at the end of the input. [My first sequence diagram tried to show all 18 turns and was unreadable.] So I picked one use case and used opt, alt and loop fragments for the guards. I learned that a good diagram leaves things out on purpose.

**Staff and orders (Staff, Waiter, Chef, Cashier, Host, Order)**
> I learned how a superclass can enforce a rule every subclass must follow: the one-task flag is private in Staff, and subclasses can only use the protected checkCanWork and finishTask. When we added the Host in Stage 3, it inherited the rule without anyone writing it again.
> My challenge was that an invalid action must not use up the staff member's turn. [My first version marked the task as done before the checks ran.] I fixed it by always throwing the exception before finishTask, so a failed action never reaches it. The second challenge was pricing order: the VIP discount comes first, then Happy Hour, and the price is locked when the order is taken. I fixed the price in Order's constructor with no setter, and checked the brief's example of a VIP burger during Happy Hour at ten dollars eighty.

**Customers and menu (Customer family, Menu, MenuItem, ComboMeal, Money)**
> I learned the value of planning extension points early. In Stage 1 we only had regular customers, but Customer was still abstract, with an overridable loss and food choice. So Stage 2 was two new subclasses and one new method, with no if-statements on customer type.
> My challenge was money and rounding. A VIP combo during Happy Hour is twenty-four thirty times 0.9 times 0.8. [Doing it in doubles gave slightly different cents.] I solved it by storing every amount in whole cents and rounding at each step in Money.multiply, which gives exactly seventeen fifty. The other challenge was keeping three class diagrams consistent with three tags. Drawing each one from that tag's code, with new and changed classes coloured, is how we spotted which changes were real and which were only cleanup.

Benjamin closes after the last reflection:
> Thanks for watching. The runs, diagrams and stage comparisons are all linked from our README.

---

## Timestamps to copy into the README

Fill these in once the clips are stitched. The approximate start times assume the lengths above.

| # | Required segment | Approx. start | Actual |
|---|---|---|---|
| 1 | The game launching from the submitted project | 0:45 | |
| 2 | Meaningful manager choices, incl. a staff-constrained decision | 1:15 (constraint ~2:50) | |
| 3 | A dish going READY → SERVED → payment | ~2:40 | |
| 4 | A VIP or Critic behaviour | ~2:10 | |
| 5 | A Stage 3 feature | 2:00 (Happy Hour, Host, combo) | |
| 6 | One victory and one defeat | 3:30 | |
| 7 | Stage tag comparisons and the diagrams | 4:00 | |

## Tips

- Rehearse your own segment once with a timer before recording.
- End your segment with the hand-off line so the stitched video flows.
- Don't read word for word. Know your three or four points and say them naturally.
