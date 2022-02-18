package br.com.bmo.tmanager.controller;

import br.com.bmo.tmanager.model.Task;
import br.com.bmo.tmanager.model.TaskStatus;
import br.com.bmo.tmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/task/")
public class TaskController {

    @Autowired
    private TaskRepository repository;

    @GetMapping("{id}")
    ResponseEntity getById(@PathVariable String id) {
        Optional<Task> taskOptional = repository.findById(id);
        if (taskOptional.isPresent())
            return new ResponseEntity(taskOptional.get(), HttpStatus.OK);
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping
    ResponseEntity<Page<Task>> getAllByStatus(@PageableDefault Pageable pageable, @RequestParam(required = true) String status) {
        Page<Task> allByStatus = repository.findAllByStatus(TaskStatus.valueOf(status), pageable);
        if (allByStatus.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<Page<Task>>(allByStatus, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<Task> newTask(@RequestBody Task requestNewTask) {
        return new ResponseEntity<Task>(repository.save(requestNewTask), HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    ResponseEntity update(@PathVariable String id, @RequestBody Task requestUpdateTask) {
        if(repository.findById(id).isEmpty())
            return new ResponseEntity(HttpStatus.NOT_FOUND);

        return new ResponseEntity(repository.save(requestUpdateTask), HttpStatus.NO_CONTENT);
    }
}
