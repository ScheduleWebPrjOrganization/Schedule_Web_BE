package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.Task;
import jakarta.transaction.Transactional;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
//    // 특정 유저의 과목 조회

//    List<Task> findByMemberId(String memberId);
//
   // 과목으로 작업 조회
    List<Task> findBySubjectId(Long subjectId);

    // 과목에 속한 모든 Task 조회
    List<Task> getTasksBySubject(Subject subject);

    // 과목에 속한 과제 삭제
    void deleteBySubjectId(Long subjectId);

    List<Task> findByMemberIdAndDateKey(Long subjectId, String dateKey);

}