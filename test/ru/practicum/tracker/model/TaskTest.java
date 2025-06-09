package ru.practicum.tracker.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.tracker.model.Status.IN_PROGRESS;
import static ru.practicum.tracker.model.Status.NEW;

public class TaskTest {

    private Task task;

    @BeforeEach
    public void setUp() {
        task = new Task("Task", "Task Description");
    }

    @Test
    public void createTask() {
        assertEquals("Task", task.getName());
        assertEquals("Task Description", task.getDescription());
        assertEquals(NEW, task.getStatus());
    }

    @Test
    public void shouldUpdateFields() {
        task.setName("Updated Name");
        task.setDescription("Updated Description");
        task.setStatus(IN_PROGRESS);

        assertEquals("Updated Name", task.getName());
        assertEquals("Updated Description", task.getDescription());
        assertEquals(IN_PROGRESS, task.getStatus());
    }

    @Test
    public void shouldBeEqualIfSameFields() {
        Task task2 = new Task("Task", "Task Description");
        task.setId(1);
        task2.setId(1);
        assertEquals(task, task2);
    }

    @Test
    public void shouldNotBeEqualToNull() {
        assertNotEquals(null, task);
    }

    @Test
    public void shouldAllowNullNameAndDescription() {
        Task nullTask = new Task(null, null);
        assertNull(nullTask.getName());
        assertNull(nullTask.getDescription());
    }
}

