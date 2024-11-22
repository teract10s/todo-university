package test.task.todolist.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import test.task.todolist.model.Task;
import test.task.todolist.model.Status;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findAllByStatusAndUserId(Status status, Long userId);

    List<Task> findAllByUserId(Long userId);
}
