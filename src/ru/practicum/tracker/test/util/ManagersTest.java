package ru.practicum.tracker.test.util;

import org.junit.jupiter.api.*;
import ru.practicum.tracker.manager.*;
import ru.practicum.tracker.util.Managers;

import static org.junit.jupiter.api.Assertions.*;

public class ManagersTest {
    private Managers managers;

    @BeforeEach
    public void setUp() {
        managers = new Managers();
    }

    @Test
    public void getDefaultShouldReturnInMemoryTaskManager() {
        TaskManager manager = managers.getDefault();
        assertNotNull(manager);
    }

    @Test
    public void getDefaultHistoryShouldReturnInMemoryHistoryManager() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertNotNull(historyManager);
    }
}
