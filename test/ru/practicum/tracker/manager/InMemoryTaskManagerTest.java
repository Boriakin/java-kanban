package ru.practicum.tracker.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.practicum.tracker.model.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static ru.practicum.tracker.model.Status.*;

public class InMemoryTaskManagerTest {
    private InMemoryTaskManager manager;

    @BeforeEach
    public void setUp() {
        manager = new InMemoryTaskManager();
    }

    @Test
    public void shouldAddAndReturnTask() {
        Task task = new Task("Task", "Task Description");
        manager.addTask(task);

        Task saved = manager.getTask(task.getId());
        assertNotNull(saved);
        assertEquals(task.getName(), saved.getName());
        assertEquals(NEW, saved.getStatus());
    }

    @Test
    public void shouldAddAndReturnEpic() {
        Epic epic = new Epic("Epic", "Epic Description");
        manager.addEpic(epic);

        Epic saved = manager.getEpic(epic.getId());
        assertNotNull(saved);
        assertEquals(epic.getName(), saved.getName());
    }

    @Test
    public void shouldAddSubtaskLinkedToEpic() {
        Epic epic = new Epic("Epic", "Epic Description");
        manager.addEpic(epic);

        Subtask subtask = new Subtask("Subtask", "Subtask Description", epic.getId());
        manager.addSubtask(subtask);

        Subtask saved = manager.getSubtask(subtask.getId());
        assertNotNull(saved);
        assertEquals(epic.getId(), saved.getEpicId());

        List<Subtask> subtasksOfEpic = manager.getSubtaskOfEpic(epic.getId());
        assertEquals(1, subtasksOfEpic.size());
    }

    @Test
    public void shouldNotAddSubtaskIfEpicNotExists() {
        Subtask subtask = new Subtask("Subtask", "No epic", 999);
        manager.addSubtask(subtask);

        assertNull(manager.getSubtask(subtask.getId()));
        assertTrue(manager.getAllSubtasks().isEmpty());
    }

    @Test
    public void shouldUpdateTask() {
        Task task = new Task("Task", "Task Description");
        manager.addTask(task);

        task.setName("Task Updated");
        manager.updateTask(task);

        assertEquals("Task Updated", manager.getTask(task.getId()).getName());
    }

    @Test
    public void shouldNotUpdateUnknownTask() {
        Task task = new Task("Unknown", "Task Description");
        task.setId(999);
        manager.updateTask(task);

        assertTrue(manager.getAllTasks().isEmpty());
    }

    @Test
    public void shouldDeleteEpicAndSubtasks() {
        Epic epic = new Epic("Epic", "Epic Description");
        manager.addEpic(epic);

        Subtask subtask = new Subtask("Subtask", "desc", epic.getId());
        manager.addSubtask(subtask);

        manager.deleteEpicById(epic.getId());

        assertNull(manager.getEpic(epic.getId()));
        assertNull(manager.getSubtask(subtask.getId()));
    }

    @Test
    public void shouldUpdateEpicStatusBasedOnSubtasks() {
        Epic epic = new Epic("Epic", "Epic Description");
        manager.addEpic(epic);

        Subtask subtask1 = new Subtask("Subtask1", "Subtask1 Description", epic.getId());
        Subtask subbtask2 = new Subtask("Subtask2", "Subtask2 Description", epic.getId());

        manager.addSubtask(subtask1);
        manager.addSubtask(subbtask2);

        subtask1.setStatus(IN_PROGRESS);
        subbtask2.setStatus(DONE);
        manager.updateSubtask(subtask1);
        manager.updateSubtask(subbtask2);

        assertEquals(IN_PROGRESS, manager.getEpic(epic.getId()).getStatus());
    }

    @Test
    public void shouldClearAll() {
        manager.addTask(new Task("Task", "Task Description"));
        manager.addEpic(new Epic("Epic", "Epic Description"));
        manager.addSubtask(new Subtask("subtask", "Subtask Description", 1)); // не добавится

        manager.deleteAllTasks();
        manager.deleteAllEpics();
        manager.deleteAllSubtasks();

        assertTrue(manager.getAllTasks().isEmpty());
        assertTrue(manager.getAllEpics().isEmpty());
        assertTrue(manager.getAllSubtasks().isEmpty());
    }

    @Test
    public void getSubtaskOfEpicShouldReturnEmptyListIfNoSubtasks() {
        Epic epic = new Epic("Epic", "Epic Description");
        manager.addEpic(epic);

        List<Subtask> subtasks = manager.getSubtaskOfEpic(epic.getId());
        assertTrue(subtasks.isEmpty());
    }
}

