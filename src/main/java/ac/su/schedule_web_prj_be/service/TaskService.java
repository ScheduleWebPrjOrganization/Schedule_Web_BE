package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Task;
import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found with id " + id));    }

    public List<Task> getTasksByMemberAndDate(Member member, LocalDate date) {
        return taskRepository.findTasksByMemberAndDate(member, date);
    }

    public void deleteTasksByMemberAndDate(String memberId, LocalDate date) {
        taskRepository.deleteTasksByMemberAndDate(memberId, date);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = getTaskById(id);
        task.setName(taskDetails.getName());
        task.setStatus(taskDetails.getStatus());
        task.setHoursToComplete(taskDetails.getHoursToComplete());
        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }
}
