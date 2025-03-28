# 🚀 Daily Planner (Backend)

**Daily Planner** is a Java-based backend application designed to efficiently manage tasks, subtasks, and epics. It provides powerful capabilities for task creation, status tracking, activity history management, and automatic status updates for epics. Ideal as a foundation for advanced task planners, backend integrations, or mobile applications.

---

## 📌 Key Features

✅ **Task Management**
- Create, update, delete, and retrieve tasks.
- Flexible management of epics and subtasks.
- Automatic epic status updates based on the state of subtasks.

🕑 **History Tracking**
- Tracks recent interactions with tasks.
- Prevents duplicates in task-view history.
- Limits history size (default is the 10 most recent tasks).

📂 **Task and Subtask Relations**
- Easily associates subtasks with epics.
- Dynamically manages task states and relationships.

🛠️ **Extensible Architecture**
- Clean and understandable codebase.
- Easy extension and modification.

---

## 📁 Project Structure

The project is organized into the following core components and interfaces:

### 🧩 Task Classes

| Class       | Description                                 |
|-------------|---------------------------------------------|
| `Task`      | Base class representing a basic task.       |
| `Epic`      | Complex task containing multiple subtasks.  |
| `Subtask`   | A task linked to a specific epic.           |

### 📚 Managers & Interfaces

| Interface/Class             | Description                                  |
|-----------------------------|----------------------------------------------|
| `TaskManager` (Interface)   | Defines essential task management methods.   |
| `InMemoryTaskManager`       | Implements in-memory storage of tasks.       |
| `HistoryManager` (Interface)| Defines task history management methods.     |
| `InMemoryHistoryManager`    | Manages history of task interactions.        |
| `Managers`                  | Provides default implementations of managers.|

---

## 📋 Implementation Details

### 📝 **Class Task**
Contains common fields and methods for all task types:
- `title` – Task name.
- `description` – Task description.
- `status` – Current task status (`NEW`, `IN_PROGRESS`, `DONE`).
- `id` – Unique identifier.

### 📂 **Class Epic**
Extends `Task`, additionally includes:
- List of related subtasks.
- Automatic status update method (`updateStatus()`).

### 📑 **Class Subtask**
Extends `Task`, includes:
- Reference to parent epic (`getParentEpic()`).

### 🗃️ **InMemoryTaskManager**
Implements the `TaskManager` interface, providing:
- In-memory storage of tasks and subtasks.
- CRUD operations for tasks.
- Integration with task interaction history.

### 📜 **InMemoryHistoryManager**
Stores history using a custom doubly-linked list (`List1`):
- Prevents duplicate tasks in history.
- Maintains a limited history size (last 10 tasks).

---

## 🎨 Task & Epic Statuses

Tasks have three statuses:

| Status           | Description                  |
|------------------|------------------------------|
| 🟢 `NEW`         | Task created but not started.|
| 🟡 `IN_PROGRESS` | Task is currently ongoing.   |
| ✅ `DONE`        | Task completed.              |

Epics automatically update their statuses based on subtasks:
- 🟢 `NEW`: All subtasks are new.
- 🟡 `IN_PROGRESS`: At least one subtask is in progress.
- ✅ `DONE`: All subtasks are completed.

---

## 🚩 Usage Example

```java
// Initialize task manager
TaskManager taskManager = Managers.getDefault();

// Create a regular task
Task task = new Task("Publish on GitHub", "Check all systems", TaskStatus.NEW);
taskManager.addTask(task);

// Create epic and subtasks
Epic epic = new Epic("Project Launch", "Full launch preparations", TaskStatus.NEW);
taskManager.addTask(epic);

Subtask subtask1 = new Subtask("Prepare presentation", "Create slides", TaskStatus.NEW, epic);
Subtask subtask2 = new Subtask("Final review", "Check all materials", TaskStatus.NEW, epic);
taskManager.addSubtask(subtask1, epic);
taskManager.addSubtask(subtask2, epic);

// Retrieve tasks and print history
taskManager.returnTaskById(task.getId());
taskManager.returnSubtask(subtask1.getId());

System.out.println("Recently viewed tasks history:");
System.out.println(((InMemoryTaskManager) taskManager).historyManager.getHistory());
```

## ⭐ Daily Planner Backend provides a robust and intuitive foundation for developing modern task and project management applications.
