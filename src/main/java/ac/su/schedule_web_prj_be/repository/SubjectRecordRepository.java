package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.SubjectRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRecordRepository extends JpaRepository<SubjectRecord, Long> {
    // 과목 아이디로 기록 조회
//    List<SubjectRecord> findBySubjectId(Long subjectId);
}