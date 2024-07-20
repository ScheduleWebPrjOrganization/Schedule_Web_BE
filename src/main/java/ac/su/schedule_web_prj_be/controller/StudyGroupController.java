package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.StudyGroup;
import ac.su.schedule_web_prj_be.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/studygroup")
public class StudyGroupController {
    private final StudyGroupService studyGroupService;

    @GetMapping()
    public List<StudyGroup> AllStudyGroup() {
        return studyGroupService.FindAllStudyGroup();
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<StudyGroup> getStudyGroupById(@PathVariable Long id) {
        StudyGroup studyGroup = studyGroupService.getStudyGroupWithMembersAndSubjects(id);
        if (studyGroup == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(studyGroup);
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


