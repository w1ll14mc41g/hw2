package view;

import model.Transaction;
import java.util.List;
import java.util.ArrayList;

public class AmountFilter implements TransactionFilter {
    private double amount;

    public AmountFilter(double amount) {
        this.amount = amount;
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filtered = new ArrayList<>();

        for (Transaction t : transactions) {
            if (t.getAmount() <= amount) {
                filtered.add(t);
            }
        }

        return filtered;
    }

}
