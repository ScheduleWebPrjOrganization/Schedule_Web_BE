package ac.su.schedule_web_prj_be.controller;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberGroupAddController {

    private MemberService memberService;

    @GetMapping("/members")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @PostMapping("/studygroup/{groupId}/addMember")
    public Member addMemberToGroup(@PathVariable Long groupId, @RequestBody Member member) {
        return memberService.addMemberToGroup(groupId, member);
    }
}
