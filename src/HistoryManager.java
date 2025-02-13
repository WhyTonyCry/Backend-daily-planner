import java.util.List;
public interface HistoryManager {
    // to add task to history
    void add(Task task);

    // to show history list
    List<Task> getHistory();

}
