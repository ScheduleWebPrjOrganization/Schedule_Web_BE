package ac.su.schedule_web_prj_be.repository;

import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.StudyGroup;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(String email);

    Optional<Member> findByName(String name);

    // 유저타입으로 검색 (관리자인지 아닌지)
    List<Member> findByUserType(String userType);

    // 특정 스터그룹에 속한 유저 검색
    List<Member> findByStudyGroup(StudyGroup studyGroupId);

    // 계정 생성 시각으로 조회
    List<Member> findByCreatedAtAfter(Date date);
    // 특정 날짜 범위 내에 생성된 멤버 검색
    List<Member> findByCreatedAtBetween(Date startDate, Date endDate);
    // 성실함 레벨로 조회
    List<Member> findByLevel(String level);


    // 스터디 그룹에 속하지 않은 멤버 검색
    List<Member> findByStudyGroupIsNull();
}
