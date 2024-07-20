package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.StudyGroup;
import ac.su.schedule_web_prj_be.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/studygroup")
public class StudyGroupController {
    private final StudyGroupService studyGroupService;

    @GetMapping()
    public List<StudyGroup> AllStudyGroup() {
        return studyGroupService.FindAllStudyGroup();
    }

    @GetMapping("/id/{id}")
    public StudyGroup getStudyGroupById(@PathVariable Long id) {
        Optional<StudyGroup> studyGroup = studyGroupService.findById(id);
        return studyGroup.orElse(null);
    }

    @GetMapping("/name/{name}")
    public StudyGroup getStudyGroupByName(@PathVariable String name) {
        if (name == null) {
            return null;
        }
        return studyGroupService.getStudyGroup(name);
    }

    @PostMapping()
    public String createStudyGroup(@RequestParam String name,
                                   @RequestParam String description,
                                   @RequestParam int memberCount) {
        StudyGroup studyGroup = studyGroupService.buildStudyGroup(name, description, memberCount);
        return "success";
    }
}


