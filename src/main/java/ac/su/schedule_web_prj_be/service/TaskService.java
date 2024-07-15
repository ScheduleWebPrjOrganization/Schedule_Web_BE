package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Task;
import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

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

    // 계획 수정
    public Task updateTask(Long id, Task task) { // Task 변경
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setName(task.getName());// 추가된 필드 설정
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("계획을 찾을 수 없습니다.");
        }
    }

    public void deleteTask(Long id) {
        Task task = getTaskById(id);
        taskRepository.delete(task);
    }

    // 계획 상태 변경
    public Task updateStatus(Long id, String status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setStatus(status);       // 상태 변경
            existingTask.setStatus(determineStatusSymbol(status)); // 상태 심볼 업데이트
            return taskRepository.save(existingTask);
        } else {
            throw new RuntimeException("계획을 찾을 수 없습니다.");
        }
    }

    // 계획 상태 변경 마크
    private String determineStatusSymbol(String status) {
        return switch (status.toLowerCase()) {
            case "complete" -> "○"; // 계획 완료
            case "in-progress" -> "△"; // 계획 진행중
            case "not-started" -> "×"; // 계획 시도를 못함
            default -> ""; // 상태가 정의되지 않은 경우 빈 문자열
        };
    }

}
