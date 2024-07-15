package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    // 특정 유저의 과목 조회
    List<Task> findByMemberId(String memberId);

   // 과목으로 작업 조회
    List<Task> findBySubjectId(Long subjectId);

    // 특정 유저의 특정 과목에 대한 작업 조회
    List<Task> findByMemberIdAndSubjectId(String memberId, Long subjectId);

}