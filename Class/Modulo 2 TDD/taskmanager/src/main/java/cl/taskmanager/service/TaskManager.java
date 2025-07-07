package cl.taskmanager.service;

import java.util.ArrayList;
import java.util.List;

import cl.taskmanager.model.Task;

public class TaskManager {
    private final List<Task> tasks = new ArrayList<>();
    private int nextId = 1;

    public Task createTask(String header, String paragraph) {
        Task task = new Task(nextId++, header, paragraph);
        tasks.add(task);
        return task;
    }

    public List<Task> getList() {
        return tasks;
    }

    public boolean updateHeader(int id, String newHeader) {
        for(Task task: tasks) {
            if (task.getId() == id) {
                task.setHeader(newHeader);
                return true;
            }   
        }
        return false;
    }

    public boolean updateParagraph(int id, String newParagraph) {
        for(Task task: tasks) {
            if (task.getId() == id) {
                task.setParagraph(newParagraph);
                return true;
            }
        }
        return false;
    }

    public boolean deleteTask(int id) {
        for (Task task:tasks) {
            if(task.getId() == id) {
                tasks.remove(task);
                return true;
            }
        }
    return false;
    }
}
