package ru.practicum.tracker.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ManagerTest {
    private Manager manager;

    @BeforeEach
    public void setUp() {
        manager = new Manager();
    }

    @Test
    public void getDefaultShouldReturnInMemoryTaskManager() {
        TaskManager manager = this.manager.getDefault();
        assertNotNull(manager);
    }

    @Test
    public void getDefaultHistoryShouldReturnInMemoryHistoryManager() {
        HistoryManager historyManager = Manager.getDefaultHistory();
        assertNotNull(historyManager);
    }
}
