package controller;

import view.ExpenseTrackerView;

import java.util.List;

import view.AmountFilter;
import view.CategoryFilter;

import model.ExpenseTrackerModel;
import model.Transaction;
public class ExpenseTrackerController {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;

  public ExpenseTrackerController(ExpenseTrackerModel model, ExpenseTrackerView view) {
    this.model = model;
    this.view = view;

    // Set up view event handlers
  }

  public void refresh() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshTable(transactions);

  }

  public void refreshFiltered() {

    // Get transactions from model
    List<Transaction> transactions = model.getTransactions();

    // Pass to view
    view.refreshFilteredTable(transactions);

  }

  public boolean addTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount)) {
      return false;
    }
    if (!InputValidation.isValidCategory(category)) {
      return false;
    }
    
    Transaction t = new Transaction(amount, category);
    model.addTransaction(t);
    view.getTableModel().addRow(new Object[]{t.getAmount(), t.getCategory(), t.getTimestamp()});
    refresh();
    return true;
  }


  // Other controller methods

  public boolean filterTransaction(double amount, String category) {
    if (!InputValidation.isValidAmount(amount) && !InputValidation.isValidCategory(category)) {
      return false;
    }

    AmountFilter amountFilter = new AmountFilter(amount);
    amountFilter.filter(model.getTransactions());

    CategoryFilter categoryFilter = new CategoryFilter(category);
    categoryFilter.filter(model.getTransactions());
    
    refreshFiltered();
    return true;
  }
}