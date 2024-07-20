package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.Task;
import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.TaskStatus;
import ac.su.schedule_web_prj_be.repository.MemberRepository;
import ac.su.schedule_web_prj_be.repository.SubjectRepository;
import ac.su.schedule_web_prj_be.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final SubjectRepository subjectRepository;

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Task not found with id " + id));
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


    // 계획 상태 변경
    public Task updateStatus(Long id, TaskStatus status) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            Task existingTask = optionalTask.get();
            existingTask.setStatus(status);       // 상태 변경
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

    public void deleteTasksBySubjectId(Long subjectId) {
    }

    // Subject에 속한 모든 Task 조회
    public List<Task> getTasksBySubjectId(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과목이 없음"));

        return taskRepository.getTasksBySubject(subject);
    }

}
