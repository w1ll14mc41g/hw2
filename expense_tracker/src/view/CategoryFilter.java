package view;

import model.Transaction;
import java.util.List;
import java.util.ArrayList;

public class CategoryFilter implements TransactionFilter {
    private String category;

    public CategoryFilter(String category) {
        this.category = category.toLowerCase();
    }

    @Override
    public List<Transaction> filter(List<Transaction> transactions) {
        List<Transaction> filtered = new ArrayList<>();

        for (Transaction t : transactions) {
            if (t.getCategory().toLowerCase().equals(category)) {
                filtered.add(t);
            }
        }

        return filtered;
    }

}
