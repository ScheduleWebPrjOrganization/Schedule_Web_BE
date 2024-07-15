package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.repository.MemberRepository;
import ac.su.schedule_web_prj_be.repository.SubjectRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final MemberService memberService;
    private final SubjectRepository subjectRepository;
    private final MemberRepository memberRepository;

    // 모든 과목 조회
    public List<Subject> getAllSubject() {
        return subjectRepository.findAll();
    }

    // 과목 id로 과목 찾기
    public Subject getSubjectById(Long id) {
        Optional<Subject> subject = subjectRepository.findById(id);
        return subject.orElseThrow(() -> new RuntimeException("과목을 찾을 수 없음"));
    }


    // 과목 추가
    public Subject addSubject(Long memberId, String subjectName) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 memberId"));
        Subject subject = new Subject();
        subject.setName(subjectName);
        subject.setMember(member);

        return subjectRepository.save(subject);
    }

    // 과목 삭제
    @Transactional
    public void deleteSubject(Long subjectId) {
        if (!subjectRepository.existsById(subjectId)) {
            throw new IllegalArgumentException("유효하지 않는 subjectId");
        }
        subjectRepository.deleteById(subjectId);
    }

    // 유저 id로 과목 조회
    // 07-16 wildmantle: subject 도메인에 memeberId 가없어서 member 객체로 검색해야할껄?
    public List<Subject> getSubjectsByMemberId(Member member) {

        return subjectRepository.findByMember(member);
    }

}
