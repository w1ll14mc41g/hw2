package view;
import model.Transaction;
import java.util.List;

public interface TransactionFilter {
    List<Transaction> filter(List<Transaction> transactions);
}
