package com.geekbrains.services;

import com.geekbrains.entities.Task;
import com.geekbrains.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService  {
    private TaskRepository taskRepository;

    public void setTaskRepository(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void printTasks()  {
        List<Task> tasksList = getAllTasksFromDB();
        for (Task t:tasksList) {
            t.printTask();
        }
    }

    public boolean isTaskInDB(Task task)  {
        return taskRepository.isTaskInDB(task);
    }

    public Task getTaskById(Long id){return  taskRepository.getTaskById(id);}

    public List<Task> getAllTasksFromDB(){
        return taskRepository.getAllTasksFromDB();
    }

    public void addTask(Task task) {
        taskRepository.addTask(task);
    }

    public void deleteTaskFromDBById(Long id) {
        taskRepository.deleteTaskFromDBById(id);
    }

    public void deleteTaskFromDBByTitle(String title) {
        taskRepository.deleteTaskFromDBByTitle(title);
    }
}




