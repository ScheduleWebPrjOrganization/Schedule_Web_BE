package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.StudyGroup;
import ac.su.schedule_web_prj_be.repository.MemberRepository;
import ac.su.schedule_web_prj_be.repository.StudyGroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final StudyGroupRepository studyGroupRepository;

    public Member getMemberById(Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(() -> new NoSuchElementException("Member not found with id " + memberId));
    }

    public Optional<Member> findName(String name) {
        return memberRepository.findByName(name);
    }

    public Member save(Member member) {
        return memberRepository.save(member);
    }

    public Member createMember(Member member) {
        return memberRepository.save(member);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


    // 아래 2개는 MemberGroupAddController 에 추가된 메소드
    // 스터디 그룹에 멤버 추가할때 모든 멤버 조회
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    // 스터디 그룹에 멤버 참여 쿼리 호출
    public void JoinStudyGroup(Long id, Member member) {
        StudyGroup studyGroup = studyGroupRepository.findById(id).get();
        List<Member> memberList = studyGroup.getMembers();
        memberList.add(member);
        studyGroup.setMembers(memberList);
        studyGroupRepository.updateStudyGroupMembersById(id, memberList);
    }

    // 그룹 참여
    public Member addMemberToGroup(Long groupId, Member member) {
        JoinStudyGroup(groupId, member);
        return memberRepository.save(member);
    }
}
