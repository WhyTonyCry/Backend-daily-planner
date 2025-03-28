import java.util.*;

public class InMemoryHistoryManager implements HistoryManager {
    private final Map<Integer, List1.Node> inHistory = new HashMap<>();
    private final List1 currentHistory = new List1();
    private int num = 0;
    // method to add task to history list
    @Override
    public void add(Task task) {
        int taskId = task.getId(); // Предполагается, что у задачи есть уникальный ID

        List1.Node oldNode = inHistory.get(taskId);
        if (oldNode != null) {
            currentHistory.removeNode(oldNode);
        }

        // Добавляем задачу в конец списка
        List1.Node newNode = currentHistory.linkLast(task);
        inHistory.put(taskId, newNode);

        // Если размер истории превышает лимит (например, 10 элементов), удаляем самый старый узел
        if (currentHistory.size() > 10) {
            List1.Node oldest = inHistory.get(0);
            if (oldest != null) {
                inHistory.remove(oldest.task.getId());
                currentHistory.removeNode(oldest);
            }
        }
    }

    // method to remove task in history list
    @Override
    public void remove (int index) {
        List1.Node node = inHistory.remove(index);
        if (node != null) {
            currentHistory.removeNode(node);
        }
    }
    // method to show history list
    @Override
    public List<Task> getHistory() {
        return currentHistory.getTasks();
    }
}


class List1 {
    static class Node {
        Task task;      // Ваша сущность задачи
        Node prev;      // Указатель на предыдущий узел
        Node next;      // Указатель на следующий узел

        public Node(Node prev, Task task, Node next) {
            this.prev = prev;
            this.task = task;
            this.next = next;
        }
    }
    private int size;
    private Node first;
    private Node last;


    public List1() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }
    // append method
    public Node linkLast(Task task) {
        Node newNode = new Node(last, task, null);
        if (last == null) {
            first = newNode;
        } else {
            last.next = newNode;
        }
        last = newNode;
        size++;
        return newNode;
    }
    // print task in ArrayList method
    public ArrayList<Task> getTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        Node current = first;
        while (current != null) {
            tasks.add(current.task);
            current = current.next;
        }
        return tasks;
    }
    // remove bu node method
    public void removeNode(Node node) {
        if (node == null) {
            return;
        }
        Node prev = node.prev;
        Node next = node.next;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
        }
        node.prev = null;
        node.next = null;
        size--;
    }
    public int size() {
        return size;
    }
}
