package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    // 유저 아이디로 과목 조회
//    List<Subject> findByMemberId(String memberId);
}