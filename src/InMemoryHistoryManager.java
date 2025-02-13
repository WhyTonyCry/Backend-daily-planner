import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new LinkedList<>();
    // method to add task to history list
    @Override
    public void add(Task task) {
        if (history.size() >= 10) {
            history.remove(0); // Удаляем самый старый элемент
        }
        history.add(task);
    }
    // method to show history list
    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}
