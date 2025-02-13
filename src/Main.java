public class Main {
    public static void main(String[] args) {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = ((InMemoryTaskManager)taskManager ).historyManager;

        Task task1 = new Task("Upload to GitHub", "Test all systems", TaskStatus.NEW);
        Task task2 = new Task("Final Test", "The last test of all systems", TaskStatus.NEW);

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        Epic epic1 = new Epic("Project Development", "Creating a new application", TaskStatus.NEW);
        Epic epic2 = new Epic("Launching Marketing Campaign", "Preparing marketing materials", TaskStatus.NEW);

        taskManager.addTask(epic1);
        taskManager.addTask(epic2);

        Subtask subtask1 = new Subtask("Design Development", "Create mockups", TaskStatus.NEW, epic1);
        Subtask subtask2 = new Subtask("New Design Development", "Create new mockups", TaskStatus.NEW, epic1);
        Subtask subtask3 = new Subtask("Backend Development", "Create server-side code", TaskStatus.NEW, epic2);

        taskManager.addSubtask(subtask1, epic1);
        taskManager.addSubtask(subtask2, epic1);
        taskManager.addSubtask(subtask3, epic2);

        System.out.println("All tasks: ");
        for (Task task : taskManager.returnAllTasks()) {
            System.out.println(task.getTitle() + "(Id: " + task.getId() + ", status: " + task.getStatus() + ")");
        }

        System.out.println("Task ID 1: " + taskManager.returnTaskById(task1.getId()));

        System.out.println("Subtasks for Epic 1: ");
        taskManager.returnSubtask(epic1.getId());

        System.out.println("Subtasks for Epic 2: ");
        taskManager.returnSubtask(epic2.getId());

        taskManager.updateTask(subtask3.getId(), TaskStatus.DONE);

        System.out.println("Epic 2 status after changing the subtask: " + epic2.getTitle() + " (status: " + epic2.getStatus() + ")");

        taskManager.updateTask(subtask1.getId(), TaskStatus.DONE);
        taskManager.updateTask(subtask2.getId(), TaskStatus.IN_PROGRESS);
        taskManager.updateTask(subtask3.getId(), TaskStatus.IN_PROGRESS);

        System.out.println("Epics' status after completing all subtasks:");
        System.out.println(epic1.getTitle() + " (status: " + epic1.getStatus() + ")");
        System.out.println(epic2.getTitle() + " (status: " + epic2.getStatus() + ")");

        System.out.println("this is ur history: " + historyManager.getHistory());

        taskManager.removeAllTasks();
        System.out.println("All tasks removed.");
    }
}