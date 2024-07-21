package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StudyGroupRepository extends JpaRepository<StudyGroup, Long> {

    // 스터디 그룹 아이디로 조회
    Optional<StudyGroup> findById(Long studyGroupId);

    Optional<StudyGroup> findByName(String name);

    @Modifying
    @Query("UPDATE StudyGroup s SET s.members = :members WHERE s.id = :id")
    void updateStudyGroupMembersById(@Param("id") Long id, @Param("members") List<Member> members);

    @Query("SELECT s FROM StudyGroup s LEFT JOIN FETCH s.members m LEFT JOIN FETCH m.subjects WHERE s.id = :id")
    Optional<StudyGroup> findByIdWithMembersAndSubjects(@Param("id") Long id);
}