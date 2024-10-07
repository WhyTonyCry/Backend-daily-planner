class Subtask extends Task {
    private final Epic parentEpic;

    public Subtask(String title, String description, String status, Epic parentEpic) {
        super(title, description, status);
        this.parentEpic = parentEpic;
    }

    public Epic getParentEpic() {
        return parentEpic;
    }

    @Override
    public String toString() {
        return "Subtask{" +
                "description='" + description + '\'' +
                ", title='" + title + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}