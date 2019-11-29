package com.geekbrains.controllers;

import com.geekbrains.entities.Task;
import com.geekbrains.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHomePage() {
        return "index";
    }

    @RequestMapping("/add_task_form")
    public String showModForm(Model model) {
        model.addAttribute("task", new Task());
        return "add_task_form";
    }

    @RequestMapping(path = "/task_add")
    public String simpleProcessForm(@ModelAttribute("task") Task task) {
        taskRepository.addTask(task);
        return "task_result";
    }

    @RequestMapping(path = "/tasks_show", method = RequestMethod.GET)
    public String showAllStudents(Model model) {
        List<Task> tasks = taskRepository.getAllTasksFromDB();
        model.addAttribute("tasks", tasks);
        return "all_tasks";
    }
}

