import java.util.List;
public interface HistoryManager {
    // to add task to history
    void add(Task task);
    // to remove task by ID
    void remove(int id);
    // to show history list
    List<Task> getHistory();

}
