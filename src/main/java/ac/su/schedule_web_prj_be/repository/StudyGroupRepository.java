package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {
//    // 스터디 그룹 아이디로 조회
//    Optional<StudyGroup> findByStudyGroupId(Long studyGroupId);
//    // 스터디 그룹 생성 최신순으로 모두 조회
//    List<StudyGroup> findAllByOrderByCreatedAtDesc();
}