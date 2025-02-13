class Task {
    protected String title;
    protected String description;
    protected int id;
    protected TaskStatus status;

    public Task(String title, String description, TaskStatus status) {
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public int getId() {
        return id;
    }

    public void setTaskId(int id) {
        this.id = id;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }



    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}