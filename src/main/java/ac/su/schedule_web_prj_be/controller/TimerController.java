package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.SubjectRecord;
import ac.su.schedule_web_prj_be.service.TimerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/schedule/subject")
public class TimerController {

    private TimerService timerService;

    @PostMapping("/start")
    public SubjectRecord startTimer(@RequestBody Subject subject) {
        return timerService.startTimer(subject);
    }

    @PostMapping("/stop/{id}")
    public SubjectRecord stopTimer(@PathVariable Long id) {
        return timerService.stopTimer(id);
    }

    @GetMapping("/elapsed/{id}")
    public long getElapsedTime(@PathVariable Long id) {
        return timerService.getElapsedTime(id);
    }
}
