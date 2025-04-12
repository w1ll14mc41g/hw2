package model;

import java.util.ArrayList;
import java.util.List;

public class ExpenseTrackerModel {

  // Changed list of transactions to private
  private List<Transaction> transactions;

  public ExpenseTrackerModel() {
    transactions = new ArrayList<>(); 
  }

  public void addTransaction(Transaction t) {
    transactions.add(t);
  }

  public void removeTransaction(Transaction t) {
    transactions.remove(t);
  }

  public List<Transaction> getTransactions() {
    List<Transaction> copy = new ArrayList(transactions);
    return copy;
  }

}