# hw2

## Added Functionality:

- Added the ability to filter transactions by amount or category *(Filters amount by transactions with less than or equal to the entered amount)*
  - Adding the ability to filter transactions by amount or category involed creating new methods within both ExpenseTrackerController.java and ExpenseTrackerView.java, in addition to creating new .java files for filtering.
- Added four test cases: One for adding multiple transactions, one for adding and removing multiple transactions, one for filtering by amount, and one for filtering by category.

## Edits to code:

- Made edits so data in the Transaction class and ExpenseTrackerModel class can't be edited outside of those classes. Those edits include:
  - Removed the setAmount and setCategory methods in Transactions.java.
  - Set the data in both the Transaction class and ExpenseTrackerModel class from public to private.
  - Changed the getTransactions() method in the ExpenseTrackerModel class so it returns a copy of the transaction list, not the original list.
