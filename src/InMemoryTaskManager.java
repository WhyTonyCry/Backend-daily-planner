import java.util.*;

public class InMemoryTaskManager implements TaskManager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Subtask> subtasks = new HashMap<>();
    public HistoryManager historyManager = Managers.getDefaultHistory();

    private static int currentId = 1;

    // method to return all tasks
    @Override
    public ArrayList<Task> returnAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // method to remove all tasks
    @Override
    public void removeAllTasks() {
        tasks.clear();
        subtasks.clear();
        currentId = 1;
    }

    @Override
    public void removeTaskByID(int id) {
        Task task = tasks.get(id);
        if (task instanceof Epic epic) {
            // Удаляем все подзадачи эпика из истории
            for (Subtask subtask : epic.getSubtasks()) {
                historyManager.remove(subtask.getId());
                subtasks.remove(subtask.getId());
            }
            // Удаляем сам эпик
            tasks.remove(id);
            historyManager.remove(id);
        } else if (task != null) {
            // Если обычная задача
            tasks.remove(id);
            historyManager.remove(id);
        } else if (subtasks.containsKey(id)) {
            subtasks.remove(id);
            historyManager.remove(id);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }


    // method to get a task by ID
    @Override
    public Task returnTaskById(int id) {
        Task task = tasks.get(id);
        if (task == null){
            task = subtasks.get(id);
        }
        if (task != null) {
            historyManager.add(task);
        }
        return task;
    }

    // method to add a task
    @Override
    public void addTask(Task task) {
        task.setTaskId(currentId);
        tasks.put(currentId, task);
        currentId++;
    }

    // method to add a subtask
    @Override
    public void addSubtask(Subtask subtask, Epic epic) {
        subtask.setTaskId(currentId);
        subtasks.put(currentId, subtask);
        epic.addSubtask(subtask);
        epic.updateStatus();
        currentId++;
    }

    // method to update a task or subtask
    @Override
    public void updateTask(int id, TaskStatus newStatus) {
        Task task = tasks.get(id);
        if (task == null) {
            task = subtasks.get(id); // check in subtasks
        }
        // check if the task is a subtask and apply changes
        if (task instanceof Subtask subtask) {
            // Update subtask status
            if (newStatus != null) {
                subtask.setStatus(newStatus);
            }
            subtasks.put(subtask.getId(), subtask);
            // Update epic status after changing subtask
            Epic epic = subtask.getParentEpic();
            if (epic != null) {
                epic.updateStatus();
            }
        } else if (task != null) {
            // Update regular task status
            task.setStatus(newStatus);
            tasks.put(task.getId(), task);
        } else {
            System.out.println("Task with ID " + id + " not found.");
        }
    }

    // method to return subtasks
    @Override
    public void returnSubtask(int subId) {
        Subtask subtask = subtasks.get(subId);
        Task task = subtask.getParentEpic();
        if (task instanceof Epic epic) {
            List<Subtask> subtasks = epic.getSubtasks();
            if (!subtasks.isEmpty()) {
                System.out.println("Subtasks for Epic " + epic.getTitle() + ":");
                for (Subtask obj : subtasks) {
                    System.out.println(" - " + obj.getTitle() + " (ID: " + obj.getId() + ", Status: "
                            + obj.getStatus() + ")");
                    historyManager.add(obj);
                }
            } else {
                System.out.println("Epic " + epic.getTitle() + " has no subtasks.");
            }
        } else {
            System.out.println("No Epic with ID: " + subtask.getParentEpic());
        }
    }
}