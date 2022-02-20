package br.com.bmo.tmanager.service.impl;

import br.com.bmo.tmanager.model.Task;
import br.com.bmo.tmanager.model.TaskStatus;
import br.com.bmo.tmanager.repository.TaskRepository;
import br.com.bmo.tmanager.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repository;

    @Override
    public Task create(Task request) {
        LocalDateTime nowDateTime = LocalDateTime.now();
        request.setStatus(TaskStatus.TODO);
        request.setCreatedAt(nowDateTime);
        request.setUpdatedAt(nowDateTime);
        return repository.save(request);
    }

    @Override
    public Optional<Task> update(String id, Task request) {
        Optional<Task> optionalTask = repository.findById(id);
        if (optionalTask.isPresent()) {
            request.setId(id);
            if (request.getShortDescription() == null)
                request.setShortDescription(optionalTask.get().getShortDescription());
            if (request.getDescription() == null)
                request.setDescription(optionalTask.get().getDescription());
            if (request.getDueDate() == null)
                request.setDueDate(optionalTask.get().getDueDate());

            request.setCreatedAt(optionalTask.get().getCreatedAt());
            request.setUpdatedAt(LocalDateTime.now());
            return Optional.of(repository.save(request));
        }
        return Optional.empty();
    }

    @Override
    public Boolean delete(String id) {
        Optional<Task> optionalTask = repository.findById(id);

        if (optionalTask.isPresent()) {
            repository.delete(optionalTask.get());
            return Boolean.TRUE;
        }

        return Boolean.FALSE;
    }

    @Override
    public Optional<Task> getById(String id) {
        return repository.findById(id);
    }

    @Override
    public Page<Task> getAllByStatus(TaskStatus status, Pageable pageable) {
        return repository.findAllByStatus(status, pageable);
    }
}
