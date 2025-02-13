public final class Managers {
    private Managers() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
    public static TaskManager getDefault() {
        return new InMemoryTaskManager();
    }
    public static HistoryManager getDefaultHistory() {
        return new InMemoryHistoryManager();
    }
}
