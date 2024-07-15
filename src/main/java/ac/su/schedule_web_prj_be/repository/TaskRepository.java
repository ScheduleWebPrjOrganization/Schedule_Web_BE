package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Subject;
import ac.su.schedule_web_prj_be.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
//    // 특정 유저의 과목 조회

//    List<Task> findByMemberId(String memberId);
//
//   // 과목으로 작업 조회
//    List<Task> findBySubjectId(Long subjectId);
//

    @Query("DELETE FROM Task t WHERE t.member.id = :memberId AND t.createdAt = :date")
    void deleteTasksByMemberAndDate(@Param("memberId") String memberId, @Param("date") LocalDate date);


    // 특정 유저의 특정 과목에 대한 작업 조회
    List<Task> findByMemberIdAndSubjectId(String memberId, Long subjectId);

    @Query("SELECT t FROM Task t WHERE t.member = :member AND DATE(t.createdAt) = :date")
    List<Task> findTasksByMemberAndDate(@Param("member") Member member, @Param("date") LocalDate date);
}