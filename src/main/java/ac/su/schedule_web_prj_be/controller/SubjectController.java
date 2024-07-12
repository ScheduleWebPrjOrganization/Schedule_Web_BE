package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.service.SubjectSerivce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subjects")
public class SubjectController {

    private final SubjectSerivce subjectSerivce;

    public SubjectController(SubjectSerivce subjectSerivce) {
        this.subjectSerivce = subjectSerivce;
    }
    // 모든 과목 조회
    @GetMapping
    public ResponseEntity<List<Subject>> getAllSubjects() {
        List<Subject> subjects = subjectSerivce.getAllSubject();
        return ResponseEntity.ok(subjects);
    }

    // 과목 id로 과목 조회
    @GetMapping("/{id}")
    public ResponseEntity<Subject> getSubject(@PathVariable Long id) {
        Subject subject = subjectSerivce.getSubjectById(id);
        return ResponseEntity.ok(subject);
    }

    // 과목 추가
    @PostMapping("/members/{memberId}")
    public ResponseEntity<Subject> addSubject(@PathVariable("memberId") String memberId, @RequestBody Subject subject) {
        Subject newSubject = subjectSerivce.addSubject(memberId, subject.getName());
        return ResponseEntity.status(HttpStatus.CREATED).body(newSubject);
    }

    // 과목 삭제
    @DeleteMapping("/{subjectId}")
    public ResponseEntity<Subject> deleteSubject(@PathVariable("subjectId") Long subjectId) {
        subjectSerivce.deleteSubject(subjectId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    // 유저 id로 과목 조회
    @GetMapping("/members/{memberId}")
    public ResponseEntity<List<Subject>> getSubjects(@PathVariable("memberId") String memberId) {
        List<Subject> subjects = subjectSerivce.getSubjectsByMemberId(memberId);
        return ResponseEntity.ok(subjects);
    }

//    GET /subjects : 모든 과목 조회
//    GET /subjects/{id} : 과목 ID로 과목 조회
//    POST /subjects/members/{memberId} : 과목 추가
//    DELETE /subjects/{subjectId} : 과목 삭제
//    GET /subjects/members/{memberId} : 유저 id로 과목 조회
}
