// package test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import model.Transaction;
import view.ExpenseTrackerView;
import view.AmountFilter;
import view.CategoryFilter;


public class TestExample {
  
  private ExpenseTrackerModel model;
  private ExpenseTrackerView view;
  private ExpenseTrackerController controller;

  @Before
  public void setup() {
    model = new ExpenseTrackerModel();
    view = new ExpenseTrackerView();
    controller = new ExpenseTrackerController(model, view);
  }

    public double getTotalCost() {
        double totalCost = 0.0;
        List<Transaction> allTransactions = model.getTransactions(); // Using the model's getTransactions method
        for (Transaction transaction : allTransactions) {
            totalCost += transaction.getAmount();
        }
        return totalCost;
    }
    


    @Test
    public void testAddTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add a transaction
        assertTrue(controller.addTransaction(50.00, "food"));
    
        // Post-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(50.00, getTotalCost(), 0.01);
    }


    @Test
    public void testRemoveTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add and remove a transaction
        Transaction addedTransaction = new Transaction(50.00, "Groceries");
        model.addTransaction(addedTransaction);
    
        // Pre-condition: List of transactions contains one transaction
        assertEquals(1, model.getTransactions().size());
    
        // Perform the action: Remove the transaction
        model.removeTransaction(addedTransaction);
    
        // Post-condition: List of transactions is empty
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());
    
        // Check the total cost after removing the transaction
        double totalCost = getTotalCost();
        assertEquals(0.00, totalCost, 0.01);
    }

    @Test
    public void testAddAnotherTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add some transactions
        assertTrue(controller.addTransaction(200.00, "food"));
        assertTrue(controller.addTransaction(100.00, "travel"));
        assertTrue(controller.addTransaction(400.00, "bills"));
        assertTrue(controller.addTransaction(50.00, "entertainment"));
        assertTrue(controller.addTransaction(300.00, "other"));
    
        // Post-condition: List of transactions contains five transactions
        assertEquals(5, model.getTransactions().size());
    
        // Check the contents of the list
        assertEquals(1050.00, getTotalCost(), 0.01);
    }

    @Test
    public void testRemoveAnotherTransaction() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());
    
        // Perform the action: Add three transactions and then remove them
        Transaction Transaction_1 = new Transaction(50.00, "entertainment");
        model.addTransaction(Transaction_1);
        Transaction Transaction_2 = new Transaction(200.00, "food");
        model.addTransaction(Transaction_2);
        Transaction Transaction_3 = new Transaction(300.00, "bills");
        model.addTransaction(Transaction_3);
    
        // Pre-condition: List of transactions contains three transactions
        assertEquals(3, model.getTransactions().size());
        double totalCost = getTotalCost();
        assertEquals(550.00, totalCost, 0.01);
    
        // Perform the action: Remove the transactions
        model.removeTransaction(Transaction_1);
        model.removeTransaction(Transaction_2);
        model.removeTransaction(Transaction_3);
    
        // Post-condition: List of transactions is empty
        List<Transaction> transactions = model.getTransactions();
        assertEquals(0, transactions.size());
    
        // Check the total cost after removing the transaction
        totalCost = getTotalCost();
        assertEquals(0.00, totalCost, 0.01);
    }

    @Test
    public void testFilterByAmount() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add some transactions
        assertTrue(controller.addTransaction(200.00, "food"));
        assertTrue(controller.addTransaction(100.00, "travel"));
        assertTrue(controller.addTransaction(400.00, "bills"));
        assertTrue(controller.addTransaction(50.00, "entertainment"));
        assertTrue(controller.addTransaction(300.00, "other"));

        // Post-condition: List of transactions contains five transactions
        assertEquals(5, model.getTransactions().size());

        // Perform the action: Set filter amount and get list of filtered transactions
        double amount = 300.00;
        AmountFilter amountFilter = new AmountFilter(amount);
        List<Transaction> filteredTransactions = amountFilter.filter(model.getTransactions());

        // Post-condition: List of transactions contains four transactions
        assertEquals(4, filteredTransactions.size());
    }

    @Test 
    public void testFilterByCategory() {
        // Pre-condition: List of transactions is empty
        assertEquals(0, model.getTransactions().size());

        // Perform the action: Add some transactions
        assertTrue(controller.addTransaction(200.00, "food"));
        assertTrue(controller.addTransaction(100.00, "travel"));
        assertTrue(controller.addTransaction(400.00, "bills"));
        assertTrue(controller.addTransaction(50.00, "entertainment"));
        assertTrue(controller.addTransaction(300.00, "other"));

        // Post-condition: List of transactions contains five transactions
        assertEquals(5, model.getTransactions().size());

        // Perform the action: Set filter category and get list of filtered transactions
        String category = "entertainment";
        CategoryFilter categoryFilter = new CategoryFilter(category);
        List<Transaction> filteredTransactions = categoryFilter.filter(model.getTransactions());

        // Post-condition: List of transactions contains four transactions
        assertEquals(1, filteredTransactions.size());
    }
    
}