package br.com.bmo.tmanager.service;

import br.com.bmo.tmanager.model.Task;
import br.com.bmo.tmanager.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface TaskService {
    public Task create(Task request);
    public Optional<Task> update(String id, Task request);
    public Optional<Task> getById(String id);
    public Page<Task> getAllByStatus(TaskStatus status, Pageable pageable);
}
