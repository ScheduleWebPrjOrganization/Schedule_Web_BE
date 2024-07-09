package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.StudyGroup;
import ac.su.schedule_web_prj_be.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class StudyGroupService {
    private final StudyGroupRepository studyGroupRepository;
    public StudyGroup buildStudyGroup(String name, String description, List<Member> members, int memberCount) {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setCreated_at(new Date());
        studyGroup.setDescription(description);
        studyGroup.setName(name);
        studyGroup.setMembers(members);
        studyGroup.setMemberCount(memberCount);

        return studyGroupRepository.save(studyGroup);
    }

    public StudyGroup buildStudyGroup(String name, String description, int memberCount) {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setCreated_at(new Date());
        studyGroup.setDescription(description);
        studyGroup.setName(name);
        studyGroup.setMemberCount(memberCount);

        return studyGroupRepository.save(studyGroup);
    }

    public void joinStudyGroup(Long id, Member member) {
        StudyGroup studyGroup = studyGroupRepository.findById(id).get();
        List<Member> memberList = studyGroup.getMembers();
        memberList.add(member);
        studyGroup.setMembers(memberList);
        studyGroupRepository.updateStudyGroupMembersById(id, memberList);
    }
}
