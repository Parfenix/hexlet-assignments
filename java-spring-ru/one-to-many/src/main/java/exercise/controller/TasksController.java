package exercise.controller;

import java.util.List;

import exercise.dto.TaskCreateDTO;
import exercise.dto.TaskDTO;
import exercise.dto.TaskUpdateDTO;
import exercise.mapper.TaskMapper;
import exercise.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import exercise.exception.ResourceNotFoundException;
import exercise.repository.TaskRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    // BEGIN
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskMapper taskMapper;

    @GetMapping("")
    List<TaskDTO> index() {
        var tasks = taskRepository.findAll();
        return tasks.stream()
                .map(task -> taskMapper.map(task))
                .toList();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO show(@PathVariable Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + id));
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    TaskDTO create(@RequestBody TaskCreateDTO taskData) {
        var task = taskMapper.map(taskData);
        var assigneeId = taskData.getAssigneeId();
        var assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + assigneeId));

        task.setAssignee(assignee);
        taskRepository.save(task);
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    TaskDTO update(@RequestBody TaskUpdateDTO taskData, @PathVariable Long id) {
        var task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found"));
        taskMapper.update(taskData, task);

        var assigneeId = taskData.getAssigneeId();
        var assignee = userRepository.findById(assigneeId)
                .orElseThrow(() -> new ResourceNotFoundException("Not Found: " + assigneeId));

        task.setAssignee(assignee);
        taskRepository.save(task);
        var taskDTO = taskMapper.map(task);
        return taskDTO;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void destroy(@PathVariable Long id) {
        taskRepository.deleteById(id);
    }
    // END
}
