# Daily Planner (Backend)

**Daily Planner** is a Java-based console backend application designed for managing tasks and subtasks. This module provides core functionality for handling task operations such as creation, deletion, status updates, and the relationship between tasks and subtasks. The backend can serve as a foundation for building task planners, project management tools, or integrating with frontend or mobile applications.

## Key Features

- **Create Tasks, Epics, and Subtasks**.
- **Delete Tasks by ID or Clear All Tasks**.
- **View Information About Individual Tasks and Subtasks**.
- **Automatic Epic Status Updates** based on the state of its subtasks.
- **Link Subtasks to Epics and Monitor Their Status**.
- **Flexible Class Structure** to manage and extend data as needed.

## Project Structure

The project consists of the following core classes:

### Classes
1. **`Task`**
   - Base class for all types of tasks. It contains:
     - `title` — task name.
     - `description` — task description.
     - `status` — current state (`NEW`, `IN_PROGRESS`, `DONE`).
     - `id` — unique task identifier.

2. **`Epic`**
   - Inherits properties from `Task`.
   - Represents a complex task containing multiple subtasks.
   - Has a method `updateStatus()` to automatically update the epic's status based on its subtasks' states.

3. **`Subtask`**
   - Inherits properties from `Task`.
   - Contains a reference to its parent `Epic`.
   - Allows adding and managing subtasks within a given epic.

4. **`Manager`**
   - A static class that handles all task and subtask operations.
   - Implements:
     - Task and subtask creation.
     - Task removal and updates.
     - Searching for tasks by ID.
     - Managing epic and subtask statuses.

## Task Statuses
   - `NEW` — a new task that hasn't started yet.
   - `IN_PROGRESS` — a task that is currently in progress.
   - `DONE` — a task that has been completed.
     
  Epics update their status automatically based on the state of their subtasks:
   - `NEW` — if all subtasks are new.
   - `IN_PROGRESS` — if at least one subtask is in progress.
   - `DONE` — if all subtasks are completed.
