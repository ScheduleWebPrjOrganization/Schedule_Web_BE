package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    // 특정 유저의 과목 조회
    List<Task> findByMember(Member member);

   // 과목으로 작업 조회
    List<Task> findBySubject(Subject subject);

    // 특정 유저의 특정 과목에 대한 작업 조회
    List<Task> findByMemberAndSubject(Member member, Subject subject);
}