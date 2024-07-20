package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.StudyGroup;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.repository.MemberRepository;
import ac.su.schedule_web_prj_be.repository.StudyGroupRepository;
import ac.su.schedule_web_prj_be.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StudyGroupService {
    private final StudyGroupRepository studyGroupRepository;
    private final MemberRepository memberRepository;
    private final SubjectRepository subjectRepository;

    public Optional<StudyGroup> getStudyGroupById(Long id) {
        return studyGroupRepository.findById(id);
    }

    // 스터디 그룹에 속한 멤버와 그 멤버의 과목 가져오기
    public StudyGroup getStudyGroupWithMembersAndSubjects(Long id) {
        Optional<StudyGroup> studyGroupOpt = studyGroupRepository.findById(id);
        if (!studyGroupOpt.isPresent()) {
            return null;
        }

        StudyGroup studyGroup = studyGroupOpt.get();
        List<Member> members = memberRepository.findByStudyGroupId(id);

        // 각 멤버에 대해 서브젝트를 가져옵니다.
        for (Member member : members) {
            List<Subject> subjects = subjectRepository.findByMemberId(member.getId());
            member.setSubjects(subjects);
        }

        studyGroup.setMembers(members);
        return studyGroup;
    }

//    public StudyGroup buildStudyGroup(String name, String description, List<Member> members, int memberCount) {
//        StudyGroup studyGroup = new StudyGroup();
//        studyGroup.setCreatedAt(LocalDateTime.now());
//        studyGroup.setDescription(description);
//        studyGroup.setName(name);
//        studyGroup.setMembers(members);
//        studyGroup.setMemberCount(memberCount);
//
//        return studyGroupRepository.save(studyGroup);
//    }

    public StudyGroup buildStudyGroup(String name, String description, int memberCount) {
        StudyGroup studyGroup = new StudyGroup();
        studyGroup.setCreatedAt(LocalDateTime.now());
        studyGroup.setDescription(description);
        studyGroup.setName(name);
        studyGroup.setMemberCount(memberCount);

        return studyGroupRepository.save(studyGroup);
    }

    public void JoinStudyGroup(Long id, Member member) {
        StudyGroup studyGroup = studyGroupRepository.findById(id).get();
        List<Member> memberList = studyGroup.getMembers();
        memberList.add(member);
        studyGroup.setMembers(memberList);
        studyGroupRepository.updateStudyGroupMembersById(id, memberList);
    }

    public List<StudyGroup> FindAllStudyGroup() {
        return studyGroupRepository.findAll();
    }

    public StudyGroup getStudyGroup(Long id) {
        try {
            return studyGroupRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public StudyGroup getStudyGroup(String name) {
        try {
            return studyGroupRepository.findByName(name).get();
        } catch (Exception e) {
            return null;
        }
    }
//    public Optional<StudyGroup> getStudyGroupById(Long id) {
//        return studyGroupRepository.findByIdWithMembersAndSubjects(id);
//    }

}
