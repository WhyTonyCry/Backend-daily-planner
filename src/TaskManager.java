import java.util.ArrayList;

public interface TaskManager {
    // method to return all tasks
    ArrayList<Task> returnAllTasks();

    // method to remove all tasks
    void removeAllTasks();

    // method to get a task by ID
    Task returnTaskById(int id);

    void removeTaskByID(int id);

    // method to add a task
    void addTask(Task task);

    // method to add a subtask
    void addSubtask(Subtask subtask, Epic epic);

    // method to update a task or subtask
    void updateTask(int id, TaskStatus newStatus);

    // method to return subtasks
    void returnSubtask(int epicId);
}
