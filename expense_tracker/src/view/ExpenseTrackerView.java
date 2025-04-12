package view;

import javax.swing.*;
import javax.swing.JFormattedTextField.AbstractFormatterFactory;
import javax.swing.table.DefaultTableModel;

import controller.InputValidation;

import java.awt.*;
import java.text.NumberFormat;

import model.Transaction;
import java.util.List;

public class ExpenseTrackerView extends JFrame {

  private JTable transactionsTable;
  private JButton addTransactionBtn;
  private JButton filterBtn;
  private JFormattedTextField amountField;
  private JTextField categoryField;
  private JFormattedTextField filterAmountField;
  private JTextField filterCategoryField;
  private DefaultTableModel model;
  

  public ExpenseTrackerView() {
    setTitle("Expense Tracker"); // Set title
    setSize(800, 600); // Make GUI larger

    String[] columnNames = {"serial", "Amount", "Category", "Date"};
    this.model = new DefaultTableModel(columnNames, 0);

    addTransactionBtn = new JButton("Add Transaction");
    filterBtn = new JButton("Filter Transactions");

    // Create UI components
    JLabel amountLabel = new JLabel("Amount:");
    NumberFormat format = NumberFormat.getNumberInstance();

    amountField = new JFormattedTextField(format);
    amountField.setColumns(10);
    
    JLabel categoryLabel = new JLabel("Category:");
    categoryField = new JTextField(10);

    // Create filter components
    JLabel filterAmountLabel = new JLabel("Filter Amount:");
    filterAmountField = new JFormattedTextField(format);
    filterAmountField.setColumns(10);
    
    JLabel filterCategoryLabel = new JLabel("Filter Category:");
    filterCategoryField = new JTextField(10);

    // Create table
    transactionsTable = new JTable(model);
  
    // Layout components
    JPanel inputPanel = new JPanel(new GridLayout(2, 3));
    
    // Add transaction panel
    JPanel addTransactionPanel = new JPanel();
    addTransactionPanel.add(amountLabel);
    addTransactionPanel.add(amountField);
    addTransactionPanel.add(categoryLabel); 
    addTransactionPanel.add(categoryField);
    addTransactionPanel.add(addTransactionBtn);
    
    // Filter panel
    JPanel filterPanel = new JPanel();
    filterPanel.add(filterAmountLabel);
    filterPanel.add(filterAmountField);
    filterPanel.add(filterCategoryLabel);
    filterPanel.add(filterCategoryField);
    filterPanel.add(filterBtn);
    
    inputPanel.add(addTransactionPanel);
    inputPanel.add(filterPanel);
  
    // Add panels to frame
    add(inputPanel, BorderLayout.NORTH);
    add(new JScrollPane(transactionsTable), BorderLayout.CENTER); 
  
    // Set frame properties
    setSize(800, 600);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public void refreshTable(List<Transaction> transactions) {
      // Clear existing rows
      model.setRowCount(0);
      // Get row count
      int rowNum = model.getRowCount();
      double totalCost = 0;
      double amount = getFilterAmount();
      String category = getFilterCategory();
      // Calculate total cost, then
      // Add rows from transactions list
      for(Transaction t : transactions) {
        if ((amount == -1 && category.isEmpty())
        || (amount == -1 && t.getCategory().equals(category))
        || (t.getAmount() <= amount && category.isEmpty())
        || (t.getAmount() <= amount && t.getCategory().equals(category))) {
          totalCost += t.getAmount();
          model.addRow(new Object[]{rowNum++, t.getAmount(), t.getCategory(), t.getTimestamp()}); 
        }
      }

      // Add total row
      Object[] totalRow = {"Total", null, null, totalCost};
      model.addRow(totalRow);
  
      // Fire table update
      transactionsTable.updateUI();
  }  

  public void refreshFilteredTable(List<Transaction> transactions) {
    double amount = getFilterAmount();
    String category = getFilterCategory();

    // Clear existing rows
    model.setRowCount(0);

    // Get row count
    int rowNum = model.getRowCount();
    double totalCost = 0;

    // Add rows from transactions list
    for (Transaction t : transactions) {
      if ((amount == -1 && t.getCategory().equals(category))
        || (t.getAmount() <= amount && category.isEmpty())
        || (t.getAmount() <= amount && t.getCategory().equals(category))) {
            model.addRow(new Object[]{rowNum++, t.getAmount(), t.getCategory(), t.getTimestamp()}); 

            // Calculate total cost of these filtered transactions only
            totalCost += t.getAmount();
      }
    }
    
    // Add total row
    Object[] totalRow = {"Total", null, null, totalCost};
    model.addRow(totalRow);

    // Fire table update
    transactionsTable.updateUI();
  }  
  
  public JButton getAddTransactionBtn() {
    return addTransactionBtn;
  }
  
  public JButton getFilterBtn() {
    return filterBtn;
  }
  
  public DefaultTableModel getTableModel() {
    return model;
  }
  
  public JTable getTransactionsTable() {
    return transactionsTable;
  }

  public double getAmountField() {
    if(amountField.getText().isEmpty()) {
      return 0;
    } else {
      double amount = Double.parseDouble(amountField.getText());
      return amount;
    }
  }

  public void setAmountField(JFormattedTextField amountField) {
    this.amountField = amountField;
  }
  
  public String getCategoryField() {
    return categoryField.getText();
  }

  public void setCategoryField(JTextField categoryField) {
    this.categoryField = categoryField;
  }
  
  public double getFilterAmount() {
    if (filterAmountField.getText().isEmpty()) {
      return -1; // Use -1 to indicate no filter
    } else {
      try {
        return Double.parseDouble(filterAmountField.getText());
      } catch (NumberFormatException e) {
        return -1;
      }
    }
  }
  
  public String getFilterCategory() {
    return filterCategoryField.getText();
  }
}