import java.util.ArrayList;

class Epic extends Task {
    private final ArrayList<Subtask> subtasks = new ArrayList<>();

    public Epic(String title, String description, String status) {
        super(title, description, status);
    }

    public void addSubtask(Subtask subtask) {
        subtasks.add(subtask);
    }

    public ArrayList<Subtask> getSubtasks() {
        return subtasks;
    }

    // update task/subtask status
    public void updateStatus() {
        boolean allNew = true;
        boolean allDone = true;

        for (Subtask subtask : subtasks) {
            if (!subtask.getStatus().equals("NEW")) {
                allNew = false;
            }
            if (!subtask.getStatus().equals("DONE")) {
                allDone = false;
            }
        }

        if (allNew) {
            this.status = "NEW";
        } else if (allDone) {
            this.status = "DONE";
        } else {
            this.status = "IN_PROGRESS";
        }
    }

    @Override
    public String toString() {
        return "Epic{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}