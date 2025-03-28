public class Main {
    public static void main(String[] args) {
        // Инициализируем менеджер задач (и вместе с ним менеджер истории)
        TaskManager taskManager = Managers.getDefault();
        // Предположим, что в InMemoryTaskManager есть публичное поле или геттер для historyManager
        HistoryManager historyManager = ((InMemoryTaskManager) taskManager).historyManager;

        // 1. Создаём две обычные задачи
        Task task1 = new Task("Upload to GitHub", "Test all systems", TaskStatus.NEW);
        Task task2 = new Task("Final Test", "The last test of all systems", TaskStatus.NEW);

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        // 2. Создаём эпики
        Epic epicWithNoSubtasks = new Epic("Empty Epic", "No subtasks here", TaskStatus.NEW);
        Epic epicWithSubtasks = new Epic("Big Epic", "Will contain 3 subtasks", TaskStatus.NEW);

        taskManager.addTask(epicWithNoSubtasks);
        taskManager.addTask(epicWithSubtasks);

        // 3. Создаём три подзадачи для второго эпика
        Subtask subtask1 = new Subtask("Subtask #1", "Details #1", TaskStatus.NEW, epicWithSubtasks);
        Subtask subtask2 = new Subtask("Subtask #2", "Details #2", TaskStatus.NEW, epicWithSubtasks);
        Subtask subtask3 = new Subtask("Subtask #3", "Details #3", TaskStatus.NEW, epicWithSubtasks);

        taskManager.addSubtask(subtask1, epicWithSubtasks);
        taskManager.addSubtask(subtask2, epicWithSubtasks);
        taskManager.addSubtask(subtask3, epicWithSubtasks);

        // 4. «Вызовем» задачи в разном порядке, чтобы заполнить историю
        // Предположим, что методы returnTaskById / returnSubtask / returnEpicById
        // вызывают historyManager.add(...) внутри, тем самым записывая историю.
        System.out.println("Get tasks in random order...");
        taskManager.returnTaskById(task2.getId());         // обращение к task2
        taskManager.returnTaskById(task1.getId());         // обращение к task1
        taskManager.returnTaskById(task1.getId());         // повторное обращение к task1
        taskManager.returnTaskById(epicWithNoSubtasks.getId()); // обращение к пустому эпику
        taskManager.returnTaskById(epicWithSubtasks.getId());   // обращение к эпику с подзадачами
        taskManager.returnSubtask(subtask1.getId());

        // 5. Смотрим, что в истории (дубликатов быть не должно)
        System.out.println("\nHistory after random queries:");
        System.out.println(historyManager.getHistory());

        // 6. Удалим из истории задачу task1
        System.out.println("\nRemoving task1 (ID = " + task1.getId() + ")...");
        taskManager.removeTaskByID(task1.getId());

        // Проверим историю снова — task1 должен исчезнуть
        System.out.println("History after removing task1:");
        System.out.println(historyManager.getHistory());

        // 7. Удалим эпик с тремя подзадачами и проверим, что эпик и подзадачи
        // тоже исчезли из истории
        System.out.println("\nRemoving epicWithSubtasks (ID = " + epicWithSubtasks.getId() + ")...");
        taskManager.removeTaskByID(epicWithSubtasks.getId());

        System.out.println("History after removing epicWithSubtasks:");
        System.out.println(historyManager.getHistory());

        // 8. Выведем оставшиеся задачи в менеджере (должен остаться task2 и epicWithNoSubtasks)
        System.out.println("\nAll tasks remaining in TaskManager:");
        for (Task t : taskManager.returnAllTasks()) {
            System.out.println(t);
        }

        taskManager.removeAllTasks();
        System.out.println("\nAll tasks removed.");
    }
}
