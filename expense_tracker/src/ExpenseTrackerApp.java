import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import controller.ExpenseTrackerController;
import model.ExpenseTrackerModel;
import view.ExpenseTrackerView;
import model.Transaction;
import controller.InputValidation;
import view.AmountFilter;
import view.CategoryFilter;

public class ExpenseTrackerApp {

  public static void main(String[] args) {
    
    // Create MVC components
    ExpenseTrackerModel model = new ExpenseTrackerModel();
    ExpenseTrackerView view = new ExpenseTrackerView();
    ExpenseTrackerController controller = new ExpenseTrackerController(model, view);

    // Initialize view
    view.setVisible(true);

    // Handle add transaction button clicks
    view.getAddTransactionBtn().addActionListener(e -> {
        double amount = view.getAmountField(); 
        String category = view.getCategoryField().toLowerCase();
        
        boolean added = controller.addTransaction(amount, category);
      
        if (!added) {
          JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
          view.toFront();
        }
    });

    // Handle filter transaction button clicks
    view.getFilterBtn().addActionListener(e -> {
      double filterAmount = view.getFilterAmount(); 
      String filterCategory = view.getFilterCategory().toLowerCase();
    
      boolean filtered = controller.filterTransaction(filterAmount, filterCategory);

      if (!filtered) {
        JOptionPane.showMessageDialog(view, "Invalid amount or category entered");
        view.toFront();
      }
    });
  }

}
