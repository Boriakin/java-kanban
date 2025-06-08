package ru.practicum.tracker.manager;

import ru.practicum.tracker.model.*;

import java.util.ArrayList;

public interface TaskManager {
    void addTask(Task task);
    void addEpic(Epic epic);
    void addSubtask(Subtask subtask);

    ArrayList<Task> getAllTasks();
    ArrayList<Epic> getAllEpics();
    ArrayList<Subtask> getAllSubtasks();

    Task getTask(int id);
    Epic getEpic(int id);
    Subtask getSubtask(int id);

    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);

    void deleteTaskById(int id);
    void deleteEpicById(int id);
    void deleteSubtaskById(int id);

    void deleteAllTasks();
    void deleteAllEpics();
    void deleteAllSubtasks();

    ArrayList<Subtask> getSubtaskOfEpic(int epicId);
}
