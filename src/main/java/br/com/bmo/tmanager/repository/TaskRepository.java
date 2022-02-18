package br.com.bmo.tmanager.repository;

import br.com.bmo.tmanager.model.Task;
import br.com.bmo.tmanager.model.TaskStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TaskRepository extends MongoRepository<Task, String> {
    Page<Task> findAllByStatus(TaskStatus status, Pageable pageable);
}
