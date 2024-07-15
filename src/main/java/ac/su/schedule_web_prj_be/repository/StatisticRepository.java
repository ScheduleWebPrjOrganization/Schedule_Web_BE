package ac.su.schedule_web_prj_be.repository;


import ac.su.schedule_web_prj_be.domain.Member;
import ac.su.schedule_web_prj_be.domain.Statistic;
import ac.su.schedule_web_prj_be.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {

    // 유저 아이디의 모든 통계 조회
    List<Statistic> findByMember(Member member);
    // 특정 과목에 대한 모든 통계 조회
    List<Statistic> findBySubject(Subject subject);
    // 특정 유저의 특정 과목 통계 조회
    List<Statistic> findByMemberAndSubject(Member member, Subject subject);
}