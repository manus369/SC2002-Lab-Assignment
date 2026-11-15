# Stage 3 — Pushing to main

## User

My group members told me that I can just push instead of waiting for them to push theirs as well

## Assistant

Okay! Since your group members have confirmed that you can push your Stage 3 changes directly to `main`, you can proceed without waiting for them.

You have already implemented:
- `HappyHour.java` — Happy Hour activation, duration and discount.
- `Restaurant.java` — `recoverWaitingCustomers()` and the Happy Hour getter.

You just need to **commit and push your changes**.

### Step 1: Check your current branch

In your VS Code PowerShell terminal, run:

```powershell
git branch --show-current
git status
```

There are two possibilities.

### If you're currently on `main`

Make sure you've saved both Java files using **Ctrl + S**.

Stage your changes:

```powershell
git add src/rush/entity/HappyHour.java src/rush/entity/Restaurant.java
```

Commit:

```powershell
git commit -m "Implement Happy Hour and satisfaction recovery"
```

Then synchronize with your teammates:

```powershell
git pull --rebase origin main
```

Finally, push:

```powershell
git push origin main
```

### If you're on `joel-stage3`

If you've already committed your changes on that branch, run:

```powershell
git switch main
git pull --ff-only origin main
git merge joel-stage3
git push origin main
```

If Git reports a merge conflict, resolve it before proceeding. Don't force-push.

### Step 2: Verify your changes on GitHub

Open your group's repository:

https://github.com/manus369/SC2002-Lab-Assignment

Refresh the page.

You should see your commit:

`Implement Happy Hour and satisfaction recovery`

You can click it to verify that both `Restaurant.java` and `HappyHour.java` were updated.

### Step 3: Inform your teammates

You can send them this message:

> I've pushed my Stage 3 P1 changes to main, including HappyHour.java and the recovery method in Restaurant.java.
>
> The recovery method calls `Customer.recover(amount)`, so that method will need to be added in Customer.java for everything to compile. The controller will also need to call the Happy Hour methods at the appropriate points during each turn.

Only send this message once your push succeeds.

**One important note:** Since `Customer.recover()` hasn't been integrated yet, the shared project may temporarily fail to compile after your push. Your teammates have agreed to this workflow, but make sure they're aware of that dependency.

**For now, run `git branch --show-current` first.** If it shows `main`, follow the first set of commands. If it shows `joel-stage3`, follow the second set.
