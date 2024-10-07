public class Main {
    public static void main(String[] args) {
        Task task1 = new Task("Upload to GitHub", "Test all systems", "NEW");
        Task task2 = new Task("Final Test", "The last test of all systems", "NEW");

        Manager.addTask(task1);
        Manager.addTask(task2);

        Epic epic1 = new Epic("Project Development", "Creating a new application", "NEW");
        Epic epic2 = new Epic("Launching Marketing Campaign", "Preparing marketing materials", "NEW");

        Manager.addTask(epic1);
        Manager.addTask(epic2);

        Subtask subtask1 = new Subtask("Design Development", "Create mockups", "NEW", epic1);
        Subtask subtask2 = new Subtask("New Design Development", "Create new mockups", "NEW", epic1);
        Subtask subtask3 = new Subtask("Backend Development", "Create server-side code", "NEW", epic2);

        Manager.addSubtask(subtask1, epic1);
        Manager.addSubtask(subtask2, epic1);
        Manager.addSubtask(subtask3, epic2);

        System.out.println("All tasks: ");
        for (Task task : Manager.returnAllTasks()) {
            System.out.println(task.getTitle() + "(Id: " + task.getId() + ", status: " + task.getStatus() + ")");
        }

        System.out.println("Task ID 1: " + Manager.returnTaskById(task1.getId()));

        System.out.println("Subtasks for Epic 1: ");
        Manager.returnSubtask(epic1.getId());

        System.out.println("Subtasks for Epic 2: ");
        Manager.returnSubtask(epic2.getId());

        Manager.updateTask(subtask3.getId(), "DONE");

        System.out.println("Epic 2 status after changing the subtask: " + epic2.getTitle() + " (status: " + epic2.getStatus() + ")");

        Manager.updateTask(subtask1.getId(), "DONE");
        Manager.updateTask(subtask2.getId(), "IN_PROGRESS");
        Manager.updateTask(subtask3.getId(), "IN_PROGRESS");

        System.out.println("Epics' status after completing all subtasks:");
        System.out.println(epic1.getTitle() + " (status: " + epic1.getStatus() + ")");
        System.out.println(epic2.getTitle() + " (status: " + epic2.getStatus() + ")");

        Manager.removeAllTasks();
        System.out.println("All tasks removed.");
    }
}