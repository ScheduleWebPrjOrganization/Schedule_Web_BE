package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.Task;
import ac.su.schedule_web_prj_be.service.MemberService;
import ac.su.schedule_web_prj_be.service.SubjectService;
import ac.su.schedule_web_prj_be.service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subjects")
public class SubjectController {

    private final SubjectService subjectService;
    private final TaskService taskService;
    private final MemberService memberService;

    public SubjectController(SubjectService subjectService, TaskService taskService, MemberService memberService) {
        this.subjectService = subjectService;
        this.taskService = taskService;
        this.memberService = memberService;
    }
    // 모든 과목 조회
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectService.getAllSubject();
        return ResponseEntity.ok(subjects);
    }

    // 과목 id로 과목 조회
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        Subject subject = subjectService.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    // 과목 추가
    @PostMapping("/members/{memberId}")
    public ResponseEntity<Subject> addSubject(@PathVariable("memberId") Long memberId, @RequestBody Subject subject) {
        Subject newSubject = subjectService.addSubject(memberId, subject.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(newSubject);
    }

    // 과목 삭제
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable("subjectId") Long subjectId) {
        subjectService.deleteSubject(subjectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 유저 id로 과목 조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable("memberId") Long memberId) {
        List<Subject> subjects = subjectService.getSubjectsByMemberId(memberService.getMemberById(memberId));
        return ResponseEntity.ok(subjects);
    }

    // Subject와 Task 연결 부분

    // Subject에 Task 추가
    @PostMapping("/{subjectId}/tasks")
    public ResponseEntity<Task> addTaskToSubject(@PathVariable("subjectId") Long subjectId, @RequestBody Task task) {
        // Subject Id로
        Subject subject = subjectService.getSubjectById(subjectId);
        task.setSubject(subject); // Task에 Subject 설정
        Task newTask = taskService.createTask(task); // Task 생성
        return ResponseEntity.status(HttpStatus.CREATED).body(newTask);
    }
    // Task가 속한 Subject 조회
    @GetMapping("/{taskId}/subject")
    public ResponseEntity<Subject> getSubjectOfTask(@PathVariable("taskId") Long taskId) {
        Task task = taskService.getTaskById(taskId);
        Subject subject = task.getSubject();
        return ResponseEntity.ok(subject);
    }



//    GET /subjects : 모든 과목 조회
//    GET /subjects/{id} : 과목 ID로 과목 조회
//    POST /subjects/members/{memberId} : 과목 추가
//    DELETE /subjects/{subjectId} : 과목 삭제
//    GET /subjects/members/{memberId} : 유저 id로 과목 조회
}
