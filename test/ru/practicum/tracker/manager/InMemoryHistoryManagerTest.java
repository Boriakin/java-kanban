package ru.practicum.tracker.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.tracker.model.Task;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class InMemoryHistoryManagerTest {

    private HistoryManager historyManager;

    @BeforeEach
    public void setUp() {
        historyManager = new InMemoryHistoryManager();
    }

    @Test
    public void shouldAddTasksToHistory() {
        Task task = new Task("Task", "Task Description");
        task.setId(1);
        historyManager.add(task);

        List<Task> history = historyManager.getHistory();
        assertEquals(1, history.size());
        assertEquals(task, history.getFirst());
    }

    @Test
    public void shouldNotAddNullTask() {
        historyManager.add(null);
        assertTrue(historyManager.getHistory().isEmpty());
    }

    @Test
    public void shouldRemoveOldestIfExceedsLimit() {
        for (int i = 0; i < 12; i++) {
            Task task = new Task("Task " + i, "Task Description");
            task.setId(i);
            historyManager.add(task);
        }

        List<Task> history = historyManager.getHistory();
        assertEquals(10, history.size());
        assertEquals("Task 2", history.getFirst().getName());
    }

    @Test
    public void shouldReturnCopyOfHistory() {
        Task task = new Task("Task", "Task Description");
        task.setId(10);
        historyManager.add(task);

        List<Task> copy = historyManager.getHistory();
        copy.clear();

        List<Task> original = historyManager.getHistory();
        assertFalse(original.isEmpty());
    }
}

