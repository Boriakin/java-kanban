package ru.practicum.tracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class SubtaskTest {

    private Subtask subtask;

    @BeforeEach
    public void setUp() {
        subtask = new Subtask("Subtask", "Subtask Description", 100);
    }

    @Test
    public void shouldCreateSubtaskWithEpicId() {
        assertEquals(100, subtask.getEpicId());
        assertEquals("Subtask", subtask.getName());
    }

    @Test
    public void shouldBeEqualIfSameFieldsAndEpicId() {
        Subtask sub2 = new Subtask("Subtask", "Subtask Description", 100);
        subtask.setId(1);
        sub2.setId(1);
        assertEquals(subtask, sub2);
    }

    @Test
    public void shouldNotBeEqualToTaskEvenWithSameFields() {
        Task task = new Task("Subtask", "Description");
        task.setId(subtask.getId());
        assertNotEquals(subtask, task);
    }
}

