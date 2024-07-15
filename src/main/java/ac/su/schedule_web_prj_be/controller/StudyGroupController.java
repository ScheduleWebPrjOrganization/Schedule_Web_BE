package ac.su.schedule_web_prj_be.controller;


import ac.su.schedule_web_prj_be.domain.StudyGroup;
import ac.su.schedule_web_prj_be.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public StudyGroup getStudyGroup(@PathVariable Long id) {
        if (id == null) {
            return null;
        }
        return studyGroupService.getStudyGroup(id);
    }

    @GetMapping("/name/{name}")
    public StudyGroup getStudyGroup(@PathVariable String name) {
        if (name == null) {
            return null;
        }
        return studyGroupService.getStudyGroup(name);
    }

    @PostMapping()
    public String createStudyGroup(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam int memberCount) {

        StudyGroup studyGroup = studyGroupService.buildStudyGroup
                (name, description, memberCount);
        return "success";
    }

    public List<StudyGroup> findStudyGroupByFilter() {
        return studyGroupService.FindAllStudyGroup();
    }
}
