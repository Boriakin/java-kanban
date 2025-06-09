package ru.practicum.tracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EpicTest {

    private Epic epic;

    @BeforeEach
    public void setUp() {
        epic = new Epic("Epic", "Epic Description");
    }

    @Test
    public void shouldAddAndRemoveSubtaskIdsPositive() {
        epic.addSubtaskId(1);
        epic.addSubtaskId(2);
        assertEquals(2, epic.getSubtaskIds().size());

        epic.removeSubtaskId(1);
        assertEquals(1, epic.getSubtaskIds().size());
    }

    @Test
    public void shouldClearSubtaskIds() {
        epic.addSubtaskId(10);
        epic.cleanSubtasksId();
        assertTrue(epic.getSubtaskIds().isEmpty());
    }

    @Test
    public void shouldNotBeEqualToTask() {
        Task task = new Task("Epic", "Epic Description");
        task.setId(epic.getId());
        assertNotEquals(epic, task);
    }

    @Test
    public void shouldHandleRemovingNonExistingSubtaskId() {
        epic.removeSubtaskId(99);
        assertTrue(epic.getSubtaskIds().isEmpty());
    }

    public void shouldBeEqualIfSameFields() {
        Epic epic2 = new Epic("Epic", "Epic Description");
        epic.setId(1);
        epic2.setId(1);
        assertEquals(epic, epic2);
    }
}
