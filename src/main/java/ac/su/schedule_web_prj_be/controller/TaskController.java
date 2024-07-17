package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.Task;
import ac.su.schedule_web_prj_be.domain.TaskStatus;
import ac.su.schedule_web_prj_be.service.MemberService;
import ac.su.schedule_web_prj_be.service.SubjectService;
import ac.su.schedule_web_prj_be.service.TaskService;
// import ac.su.schedule_web_prj_be.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;
    private final SubjectService subjectService;

    @PostMapping
    public ResponseEntity<Task> createTask(@RequestBody Task task) {
        Task createdTask = taskService.createTask(task);
        return ResponseEntity.status(201).body(createdTask);
    }


    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Task task = taskService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    //    @GetMapping("/member/{memberId}/date/{date}")
//    public ResponseEntity<List<Task>> getTasksByMemberAndDate(@PathVariable Long memberId, @PathVariable String date) {
//        // 더미 데이터를 사용하여 Member 객체를 가져오는 부분을 대체
//        // Member member = memberService.getMemberById(memberId);
//
//        LocalDate localDate = LocalDate.parse(date);
//
//        // 더미 데이터 생성, 수정해야함.
//        List<Task> dummyTasks = new ArrayList<>();
//        dummyTasks.add(new Task("Task 1", TaskStatus.DONE, 2, null, null, "2024-07-04"));
//        dummyTasks.add(new Task("Task 2", TaskStatus.IN_PROGRESS, 3, null, null,"2024-07-05"));
//        dummyTasks.add(new Task("Task 3", TaskStatus.NOT_DONE, 1, null, null,"2024-07-06"));
//
//        return ResponseEntity.ok(dummyTasks);
//    }
//    //    @PostMapping("/user/{memberId}/date/{date}")
//    public ResponseEntity<Task> createTask(@PathVariable String memberId, @PathVariable String date, @RequestBody Task task) {
//        task.setCreatedAt(LocalDate.parse(date)); // 날짜 설정
//        Task createdTask = taskService.createTask(task, memberId);
//        return ResponseEntity.status(201).body(createdTask);
//    }
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task taskDetails) {
        Task updatedTask = taskService.updateTask(id, taskDetails);
        return ResponseEntity.ok(updatedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{taskId}/subject")
    public ResponseEntity<Subject> getSubjectofTask(@PathVariable("taskId") Long taskId) {
        Task task = taskService.getTaskById(taskId);
        Subject subject = task.getSubject();
        return ResponseEntity.ok(subject);
    }

    @GetMapping("/tasks/date/{date}")
    public ResponseEntity<List<Task>> getTasksByDate(@PathVariable("date") String dateKey) {
        List<Task> tasks = taskService.getTasksByDate(dateKey);
        return ResponseEntity.ok(tasks);
    }
}
