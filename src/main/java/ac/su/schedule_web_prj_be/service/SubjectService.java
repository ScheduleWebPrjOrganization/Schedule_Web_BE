package ac.su.schedule_web_prj_be.service;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.Task;
import ac.su.schedule_web_prj_be.repository.MemberRepository;
import ac.su.schedule_web_prj_be.repository.SubjectRepository;
import ac.su.schedule_web_prj_be.repository.TaskRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final MemberService memberService;
    private final SubjectRepository subjectRepository;
    private final MemberRepository memberRepository;
    private final TaskRepository taskRepository;

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
        subjectRepository.deleteById(subjectId);
    }

    // Subject에 Task추가
    public Task addTaskToSubject(Long subjectId, Task task) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("Subject가 없음"));
        task.setSubject(subject);
        task.setCreatedAt(LocalDate.now());

        return taskRepository.save(task);
    }
    // Subject에 속한 모든 Task 조회
    public List<Task> getTasksBySubjectId(Long subjectId) {
        Subject subject = subjectRepository.findById(subjectId)
                .orElseThrow(() -> new IllegalArgumentException("해당 ID의 과목이 없음"));

        return taskRepository.getTasksBySubject(subject);
    }

    // 유저 id로 과목 조회
    // 07-16 wildmantle: subject 도메인에 memeberId 가없어서 member 객체로 검색해야할껄?
    public List<Subject> getSubjectsByMemberId(Member member) {

        return subjectRepository.findByMember(member);
    }

}
