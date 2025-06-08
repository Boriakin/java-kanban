package ru.practicum.tracker.manager;

import ru.practicum.tracker.model.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {
    private final List<Task> history = new ArrayList<>();


    @Override
    public void add(Task task) {
        if (task != null) {
            history.add(task);
        }
        if (history.size() > 10) {
            history.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return new ArrayList<>(history);
    }
}