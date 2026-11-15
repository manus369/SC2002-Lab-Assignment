# Stage 1 — Git commits, pushing and Pull Requests

## User

After doing git commit -m "Improve code readability and encapsulation"

```text
[main aafd4b0] Improve code readability and encapsulation
 4 files changed, 40 insertions(+), 25 deletions(-)
```

I don't see it updated yet on github?

## Assistant

That's normal! Your commit was successful, but **`git commit` only saves your changes locally on your computer**. It does not automatically upload them to GitHub.

You need to use `git push` to upload your changes.

### Step 1: Push your changes to GitHub

Since your output shows `[main aafd4b0]`, you're currently working on the `main` branch.

Run this in your VS Code terminal:

```powershell
git push origin main
```

This uploads your local commits to the `main` branch of your GitHub repository.

### Step 2: Verify that the push succeeded

You should see something similar to:

```text
To https://github.com/manus369/sc2002-lab-poject.git
   abc1234..aafd4b0  main -> main
```

Then open your repository:

https://github.com/manus369/sc2002-lab-poject

Refresh the page. You should now see your commit:

```text
Improve code readability and encapsulation
```

You can click the commit to see the four files you modified.

### Step 3: If GitHub rejects your push

Because this is a group project, you might encounter:

```text
! [rejected] main -> main (fetch first)
```

This means your teammates have uploaded changes that aren't on your computer yet.

If this happens, **don't use `git push --force`**, as it could overwrite your teammates' work.

Instead, share the error message so we can resolve it safely.

### Difference between commit and push

`git add` and `git commit` record changes on your computer; `git push` sends the commits to GitHub.

**For now, just run:**

```powershell
git push origin main
```

Once it succeeds, your changes will appear on GitHub.

---

## User

```text
PS C:\Users\joelk\OneDrive\Desktop\NTU\SC2002\sc2002-lab-poject> git push origin main
info: please complete authentication in your browser...
remote: Permission to manus369/sc2002-lab-poject.git denied to Baquaaa.
fatal: unable to access 'https://github.com/manus369/sc2002-lab-poject.git/': The requested URL returned error: 403
```

Got this error

## Assistant

Your commit was successful, but GitHub is preventing you from uploading it because **your GitHub account doesn't appear to have permission to modify the repository**.

The important part of your error is:

```text
remote: Permission to manus369/sc2002-lab-poject.git denied to Baquaaa.
fatal: unable to access ... 403
```

This tells us:
- **`manus369`** is the owner of the GitHub repository.
- **`Baquaaa`** is the GitHub account you're currently authenticated with.
- **`403`** means GitHub has denied your attempt to push changes.

Your changes are still safely saved on your computer in commit `aafd4b0`. You don't need to repeat the commit.

### Solution 1: Ask your teammate to add you as a collaborator

Since this is your SC2002 group assignment, the easiest solution is for the repository owner (`manus369`) to give your GitHub account (`Baquaaa`) write access.

Ask your teammate to:
1. Open https://github.com/manus369/sc2002-lab-poject.
2. Click **Settings**.
3. Navigate to **Collaborators** (under Access).
4. Click **Add people**.
5. Search for `Baquaaa` and send an invitation.

You will need to **accept the invitation** through GitHub or your email.

Once accepted, return to your VS Code terminal and run:

```powershell
git push origin main
```

If the repository permits collaborators to push to `main`, this should upload your changes.

### Solution 2: If you've already been added as a collaborator

Check that:
1. You have accepted the GitHub invitation.
2. `Baquaaa` is the account your teammate invited.
3. Your VS Code terminal is authenticated with that same GitHub account.

If `Baquaaa` is not your actual GitHub account, you may need to sign out of the incorrect account and authenticate again.

### Alternative: Use a Pull Request

If your teammate prefers not to grant direct write access, you can **fork the repository**, push your changes to your own fork, and create a pull request.

However, for your group assignment, I recommend Solution 1 because it makes collaboration simpler.

**For now, ask your teammate whether `Baquaaa` has been added as a collaborator.** That's the first thing to check before changing any Git settings.

---

## User

My teammate informed me to make a branch and use a pull request, how do I get to doing that?

## Assistant

Since your teammate wants you to use a **branch and Pull Request (PR)**, you can submit your code changes for review without directly modifying the group's `main` branch.

You already committed your changes successfully:

```text
[main aafd4b0] Improve code readability and encapsulation
 4 files changed, 40 insertions(+), 25 deletions(-)
```

**You do not need to redo your modifications or commit.** We can use your existing commit.

### Step 1: Create your own branch

In your VS Code PowerShell terminal, run:

```powershell
git switch -c joel-optimizations
```

This creates a new branch called `joel-optimizations` containing your existing commit.

Verify it:

```powershell
git branch
```

You should see something like:

```text
* joel-optimizations
  main
```

The `*` indicates your current branch.

### Step 2: Upload your branch to GitHub

Normally, you would run:

```powershell
git push -u origin joel-optimizations
```

However, **you previously received a 403 permission error**, which means GitHub didn't allow your account `Baquaaa` to push to `manus369/sc2002-lab-poject`.

You can try the command above if your teammate has since granted you write access.

If you still get `403`, follow Step 3.

### Step 3: Fork the repository

Forking creates your own copy of the repository, where you can upload your changes and submit a Pull Request to the original project.

1. Open https://github.com/manus369/sc2002-lab-poject.
2. Click **Fork** at the top-right.
3. Select your account, `Baquaaa`, as the owner.
4. Click **Create fork**.

Your fork should have this address:

https://github.com/Baquaaa/sc2002-lab-poject

Now return to VS Code and connect your existing local project to your fork:

```powershell
git remote add fork https://github.com/Baquaaa/sc2002-lab-poject.git
```

Then push your branch to your own fork:

```powershell
git push -u fork joel-optimizations
```

This uploads the branch and its existing commit to your GitHub account without changing your teammate's repository.

### Step 4: Create the Pull Request

Go to your group's repository:

https://github.com/manus369/sc2002-lab-poject/pulls

Click **New pull request**, then **compare across forks** if necessary.

Make sure the settings are:

| Setting | Value |
|---|---|
| Base repository | `manus369/sc2002-lab-poject` |
| Base branch | `main` |
| Head repository | `Baquaaa/sc2002-lab-poject` if using a fork |
| Compare branch | `joel-optimizations` |

If Step 2 succeeded, select `manus369/sc2002-lab-poject` as both repositories, with `main` as the base and `joel-optimizations` as the compare branch.

GitHub will display the files you've changed. Review them before submitting.

### Step 5: Give the Pull Request a title and description

You can use something like this:

**Title:** Improve code readability and encapsulation

**Description:**

Made a few small improvements to the existing code:
- Replaced repeated string concatenation with `StringBuilder` in `ConsoleUI`.
- Added `final` to attributes that should not be reassigned.
- Improved encapsulation of restaurant data.
- Updated table references for better state consistency.

**Testing:**
- Stage 3 victory test passed.
- Stage 3 defeat test passed.
- Both tests produced the expected results.

Make sure the description matches the actual changes in your commit.

Click **Create pull request**.

Your teammate can now review the modifications and merge them into `main`.

### How the workflow works

Create a branch, commit changes, submit a PR, and merge after review.

**For now, start by running these two commands:**

```powershell
git switch -c joel-optimizations
git push -u origin joel-optimizations
```

If the push succeeds, you can skip forking and go directly to Step 4. If you get the same `403` error, follow Step 3 to push through your own fork instead.
