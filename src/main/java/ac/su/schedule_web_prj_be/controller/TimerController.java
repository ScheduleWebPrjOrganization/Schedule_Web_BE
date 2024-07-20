package ac.su.schedule_web_prj_be.controller;


import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.SubjectRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import ac.su.schedule_web_prj_be.service.TimerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/timer")
public class TimerController {

    @Autowired
    private TimerService timerService;

    // 타이머 시작
    @GetMapping("/start")
    public ResponseEntity<Void> startTimer(@RequestParam("subject_id") Long subjectId) {
        timerService.startTimer(subjectId);
        return ResponseEntity.ok().build();
    }

    // 타이머 일시정지
    @GetMapping("/pause")
    public ResponseEntity<Void> pauseTimer() {
        timerService.pauseTimer();
        return ResponseEntity.ok().build();
    }

    // 타이머 재시작
    @GetMapping("/resume")
    public ResponseEntity<Void> resumeTimer() {
        timerService.resumeTimer();
        return ResponseEntity.ok().build();
    }

    // 타이머 정지 및 String 타입으로 시간:분:초 형식으로 http://localhost:8080/api/timer/stop 에 시간 출력
    // 타이머 정지 및 프론트에서 subject_id 받아옴
    @GetMapping("/stop")
    public ResponseEntity<String> stopTimer(@RequestParam("subject_id") Long subjectId) {
        String formattedTime = timerService.stopTimer(subjectId);
        return ResponseEntity.ok(formattedTime);
    }
}
//@RestController
//@RequestMapping("/api/timer")
//public class TimerController {
//
//    private TimerService timerService;
//
//    @PostMapping("/start")
//    public SubjectRecord startTimer(@RequestBody Subject subject) {
//        return timerService.startTimer(subject);
//    }
//
//    @PostMapping("/stop")
////    @PostMapping("/stop/{id}")
//    public SubjectRecord stopTimer(@PathVariable Long id) {
//        return timerService.stopTimer(id);
//    }
//
//    // 일시정지 기능
//    @PostMapping("/pause")
////    @PostMapping("/pause/{id}")
//    public SubjectRecord pauseTimer(@PathVariable Long id) {
//        return timerService.pauseTimer(id);
//    }
//
//    // 일시정지 재개 기능
//    @PostMapping("/resume")
////    @PostMapping("/resume/{id}")
//    public SubjectRecord resumeTimer(@PathVariable Long id) {
//        return timerService.resumeTimer(id);
//    }
//
//    // 시간 계산
//    @GetMapping("/elapsed/{id}")
//    public long getElapsedTime(@PathVariable Long id) {
//        return timerService.getElapsedTime(id);
//    }
//
//    // 현재 공부중인 과목과 시간을 알려주는 기능
//    @GetMapping("/current")
//    public List<SubjectRecord> getCurrentStudyingSubjects() {
//        return timerService.getCurrentStudyingSubjects();
//    }
//
//    // 과목 통합 공부 시간 기능
//    @GetMapping("/total/{subjectId}")
//    public long getTotalStudyTimeForSubject(@PathVariable Long subjectId) {
//        Subject subject = new Subject();
//        subject.setId(subjectId);
//        return timerService.getTotalStudyTimeForSubject(subject);
//    }
//
//    // 새로고침시 공부중인 과목, 시간을 갱신하는 기능
//    @GetMapping("/refresh")
//    public List<SubjectRecord> refreshStudyData() {
//        return timerService.getCurrentStudyingSubjects();
//    }
//}
