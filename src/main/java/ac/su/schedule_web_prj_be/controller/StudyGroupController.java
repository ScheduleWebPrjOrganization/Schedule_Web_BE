package ac.su.schedule_web_prj_be.controller;


import ac.su.schedule_web_prj_be.domain.StudyGroup;
import ac.su.schedule_web_prj_be.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/studygroup")
public class StudyGroupController {
    private final StudyGroupService studyGroupService;

    @GetMapping()
    public String AllStudyGroup() {
        return studyGroupService.FindAllStudyGroup().toString();
    }

    @PostMapping()
    public String createStudyGroup(@RequestParam String name,
                                 @RequestParam String description,
                                 @RequestParam int memberCount) {

        StudyGroup studyGroup = studyGroupService.buildStudyGroup
                (name, description, memberCount);

        return "success";
    }


}
