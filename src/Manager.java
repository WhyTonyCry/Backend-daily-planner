import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class Manager {
    static Map<Integer, Task> tasks = new HashMap<>();
    static Map<Integer, Subtask> subtasks = new HashMap<>();

    private static int currentId = 1;

    // method to return all tasks
    static ArrayList<Task> returnAllTasks() {
        return new ArrayList<>(tasks.values());
    }

    // method to remove all tasks
    static void removeAllTasks() {
        tasks.clear();
        subtasks.clear();
        currentId = 1;
    }

    // method to get a task by ID
    static Task returnTaskById(int id) {
        if (tasks.get(id) == null){
            return subtasks.get(id);
        } else {
            return tasks.get(id);
        }
    }

    // method to add a task
    static void addTask(Task task) {
        task.setTaskId(currentId);
        tasks.put(currentId, task);
        currentId++;
    }

    // method to add a subtask
    static void addSubtask(Subtask subtask, Epic epic) {
        subtask.setTaskId(currentId);
        subtasks.put(currentId, subtask);
        epic.addSubtask(subtask);
        epic.updateStatus();
        currentId++;
    }

    // method to update a task or subtask
    static void updateTask(int id, String newStatus) {
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
    static void returnSubtask(int epicId) {
        Task task = tasks.get(epicId);
        if (task instanceof Epic epic) {
            List<Subtask> subtasks = epic.getSubtasks();
            if (!subtasks.isEmpty()) {
                System.out.println("Subtasks for Epic " + epic.getTitle() + ":");
                for (Subtask obj : subtasks) {
                    System.out.println(" - " + obj.getTitle() + " (ID: " + obj.getId() + ", Status: "
                            + obj.getStatus() + ")");
                }
            } else {
                System.out.println("Epic " + epic.getTitle() + " has no subtasks.");
            }
        } else {
            System.out.println("No Epic with ID: " + epicId);
        }
    }
}